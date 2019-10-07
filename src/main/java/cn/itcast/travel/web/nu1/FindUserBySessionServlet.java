//package cn.itcast.travel.web.servlet;
//
//import cn.itcast.travel.domain.User;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebServlet("/findUserBySessionServlet")
//public class FindUserBySessionServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //获取SESSION对象
//        HttpSession session = request.getSession();
//        //从session中获取user
//        User user = (User) session.getAttribute("user");
//        //将user转成json 数据
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(user);
//        //设置响应的数据类型
//        response.setContentType("application/json;charset=utf-8");
//        //响应数据
//        response.getWriter().write(json);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doPost(request,response);
//    }
//}
