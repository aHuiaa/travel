//package cn.itcast.travel.web.servlet;
//
//import cn.itcast.travel.domain.ResultInfo;
//import cn.itcast.travel.domain.User;
//import cn.itcast.travel.service.UserService;
//import cn.itcast.travel.service.impl.UserServiceImpl;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.beanutils.BeanUtils;
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
//@WebServlet("/registerServlet")
//public class RegisterServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String checkcode ="";
//        HttpSession session = request.getSession();
//        checkcode = (String) session.getAttribute("CHECKCODE_SERVER");
//        session.removeAttribute("CHECKCODE_SERVER");
//        String check = request.getParameter("check");
//        ResultInfo resultInfo = new ResultInfo();
//        if (!checkcode.equalsIgnoreCase(check)) {
//            resultInfo.setFlag(false);
//            resultInfo.setErrorMsg("验证码错误，请重试！");
//        }else{
//            //1.获取数据
//            Map<String, String[]> map = request.getParameterMap();
//
//            //2. 封装数据
//            User user = new User();
//            try {
//                BeanUtils.populate(user, map);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//
//
//            //3.调用service完成注册
//            UserService service = new UserServiceImpl();
//            boolean flag = service.register(user);
//
//            if (flag) {
//                //如果flag为true，说明注册成功
//                resultInfo.setFlag(true);
//
//            } else {
//                // 如果flag为false，说明已经存在用户，注册失败
//                resultInfo.setFlag(false);
//                resultInfo.setErrorMsg("注册失败，请重试！");
//            }
//        }
//        //将数据序列化为JSON数据
//        ObjectMapper mapper = new ObjectMapper();
//        String info = mapper.writeValueAsString(resultInfo);
//
//        //设置响应数据的编码格式
//        response.setContentType("application/json;charset = utf-8");
//
//        //4. 响应结果
//        response.getWriter().write(info);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doPost(request,response);
//    }
//}
