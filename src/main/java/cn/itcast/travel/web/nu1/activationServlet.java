//package cn.itcast.travel.web.servlet;
//
//import cn.itcast.travel.service.UserService;
//import cn.itcast.travel.service.impl.UserServiceImpl;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/activationServlet")
//public class activationServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //获取邮件发来的验证码
//        String code = request.getParameter("code");
//        String username = request.getParameter("username");
//
//        //调用service方法查询用户的code是否正确，并作出相应的判断
//        UserService service = new UserServiceImpl();
//        //定义一个boolean变量用于接收是否激活成功
//        boolean flag = false;
//        flag = service.activation(username,code);
//        response.setContentType("text/html;charset=utf-8");
//        //如果激活成功，页面跳转到登录页面
//        if (flag){
//            response.getWriter().write("<h2>激活成功,点击<a href = '"+request.getContextPath()+"/login.html"+"'>登录</a></h2>");
//        }else{
//            response.sendRedirect(request.getContextPath()+"/index.html");
//         //response.getWriter().write("alert('操作不合法！')");
//        }
//
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doPost(request,response);
//    }
//}
