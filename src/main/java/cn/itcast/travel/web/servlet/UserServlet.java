package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseSevlet{

    /**
     * 用户注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String checkcode =null;
        HttpSession session = request.getSession();
        checkcode = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        String check = request.getParameter("check");
        ResultInfo resultInfo = new ResultInfo();
        if (checkcode == null || !checkcode.equalsIgnoreCase(check)) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误，请重试！");
        }else{
            //1.获取数据
            Map<String, String[]> map = request.getParameterMap();

            //2. 封装数据
            User user = new User();
            try {
                BeanUtils.populate(user, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            //3.调用service完成注册
            UserService service = new UserServiceImpl();
            boolean flag = service.register(user);

            if (flag) {
                //如果flag为true，说明注册成功
                resultInfo.setFlag(true);
            } else {
                // 如果flag为false，说明已经存在用户，注册失败
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("注册失败，请重试！");
            }
        }
        //将数据序列化为JSON数据
        ObjectMapper mapper = new ObjectMapper();
        String info = mapper.writeValueAsString(resultInfo);

        //设置响应数据的编码格式
        response.setContentType("application/json;charset = utf-8");

        //4. 响应结果
        response.getWriter().write(info);
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码验证
        String code = request.getParameter("check");
        HttpSession session = request.getSession();
        String session_code = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        ResultInfo info = new ResultInfo();
        //将两个获取到的验证码进行比较，看是否相同
        if (!code.equalsIgnoreCase(session_code) || session_code == null){
            info.setErrorMsg("验证码错误！");
            info.setFlag(false);
        }else {
            //获取数据
            Map<String, String[]> map = request.getParameterMap();
            User login_user = new User();
            //封装数据
            try {
                BeanUtils.populate(login_user, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //调用service方法
            UserService service = new UserServiceImpl();
            User user = service.login(login_user.getUsername(), login_user.getPassword());
            if (user == null) {
                info.setFlag(false);
                info.setErrorMsg("用户名或密码错误！");
            } else {
                info.setFlag(true);
                //将返回的user存到session中去
                session.setAttribute("user",user);
            }
        }
        //设置响应数据的格式
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //响应数据
        response.getWriter().write(json);
    }

    /**
     * //通过session查找用户，可以判断用户是否登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findUserBySession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取SESSION对象
        HttpSession session = request.getSession();
        //从session中获取user
        User user = (User) session.getAttribute("user");
        //将user转成json字符串 数据
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        //设置响应的数据类型
        response.setContentType("application/json;charset=utf-8");
        //响应数据
        response.getWriter().write(json);
    }

    /**
     * 用户退出
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    /**
     * 激活用户账号
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void activation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取邮件发来的验证码
        String code = request.getParameter("code");
        String username = request.getParameter("username");
        //调用service方法查询用户的code是否正确，并作出相应的判断
        UserService service = new UserServiceImpl();
        //定义一个boolean变量用于接收是否激活成功
        boolean flag = false;
        flag = service.activation(username, code);
        response.setContentType("text/html;charset=utf-8");
        //如果激活成功，页面跳转到登录页面
        if (flag) {
            response.getWriter().write("<h2>激活成功,点击<a href = '" + request.getContextPath() + "/login.html" + "'>登录</a></h2>");
        } else {
            response.sendRedirect(request.getContextPath() + "/index.html");
        }
    }
}
