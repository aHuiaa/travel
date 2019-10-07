//package cn.itcast.travel.web.servlet;
//
//import cn.itcast.travel.domain.ResultInfo;
//import cn.itcast.travel.domain.User;
//import cn.itcast.travel.service.UserService;
//import cn.itcast.travel.service.impl.UserServiceImpl;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.beanutils.BeanUtils;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.util.Map;
//
//@WebServlet("/loginServlet")
//public class loginServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        //验证码验证
//        String code = req.getParameter("check");
//        HttpSession session = req.getSession();
//        String session_code = (String) session.getAttribute("CHECKCODE_SERVER");
//        session.removeAttribute("CHECKCODE_SERVER");
//        ResultInfo info = new ResultInfo();
//          //将两个获取到的验证码进行比较，看是否相同
//        if (!code.equalsIgnoreCase(session_code) || session_code == null){
//            info.setErrorMsg("验证码错误！");
//            info.setFlag(false);
//        }else {
//            //获取数据
//            Map<String, String[]> map = req.getParameterMap();
//            User login_user = new User();
//            //封装数据
//            try {
//                BeanUtils.populate(login_user, map);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//            //调用service方法
//            UserService service = new UserServiceImpl();
//            User user = service.login(login_user.getUsername(), login_user.getPassword());
//            if (user == null) {
//                info.setFlag(false);
//                info.setErrorMsg("用户名或密码错误！");
//            } else {
//                info.setFlag(true);
//                //将返回的user存到session中去
//                session.setAttribute("user",user);
//            }
//        }
//
//        resp.setContentType("application/json;charset=utf-8");
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(info);
//
//
//        //响应数据
//        resp.getWriter().write(json);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.doPost(req,resp);
//    }
//}
