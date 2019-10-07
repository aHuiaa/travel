package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseSevlet{

    /**
     * 分页查找旅游线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findRouteByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int rows = Integer.parseInt(request.getParameter("rows"));
        int cid = Integer.parseInt(request.getParameter("cid"));
        String cond = request.getParameter("condition");
        String condition = null;
        //如果不为空才进行转码，不然会出错
        if (cond != null) {
             condition = new String(cond.getBytes("iso-8859-1"), "utf-8");
        }
        request.setAttribute("condition",condition);
        if (currentPage < 1 || "".equals(currentPage)){
            currentPage = 1;
        }

        //调用service方法
        RouteService service = new RouteServiceImpl();
        PageBean<Route> pageBean = service.findRouteByPage(currentPage, rows,cid,condition);
        System.out.println(pageBean);

        //将返回的pagebean对象序列化成json对象，并响应
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),pageBean);
    }

    /**
     * 通过线路id查询详情
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findRouteDetailByRid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取详情页的rid
        String rid = request.getParameter("rid");

        //调用service方法
        //查询旅游路线
        RouteService service = new RouteServiceImpl();
        Route route = service.findRouteDetail(rid);


        //将route序列化为Json对象
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),route);
    }

    /**
     * 收藏路线
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void favorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取数据 路线的rid，用户的uid，访问service，保存收藏的数据
        String rid = request.getParameter("rid");
        String uid = request.getParameter("uid");

        FavoriteService service = new FavoriteServiceImpl();
        service.favorite(rid,uid);
    }

    /**
     * 取消收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void noFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取数据 路线的rid，用户的uid，访问service，保存收藏的数据
        String rid = request.getParameter("rid");
        String uid = request.getParameter("uid");

        //调用service
        FavoriteService service = new FavoriteServiceImpl();
        service.noFavorite(rid,uid);
    }

    /**
     * 查询用户是否收藏过
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int uid = user.getUid();
        String rid = request.getParameter("rid");
        FavoriteService service = new FavoriteServiceImpl();
        Favorite favorite = service.findFavorite(rid,uid);

        response.setContentType("application/json;charset:UTF-8");

        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(response.getOutputStream(),favorite);
    }
}
