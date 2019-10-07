package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.*;
import cn.itcast.travel.dao.impl.*;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    RouteDao routeDao = new RouteDaoImpl();
    RouteImgDao routeImgDao = new RouteImgDaoImpl();
    SellerDao sellerDao = new SellerDaoImpl();
    CategoryDao categoryDao = new CategoryDaoImpl();
    FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageBean<Route> findRouteByPage(int currentPage, int rows ,int cid ,String condition) {

        //查询总记录数
        int totalCount = routeDao.findTotalCount(cid , condition);

        //计算总页数   总页数 = 总记录数 / 每页的记录数
        int totalPage = (totalCount%rows == 0 ) ?  totalCount / rows : (totalCount / rows) + 1;

        if (currentPage>totalPage){
            currentPage = totalPage;
        }
        if (currentPage < 1 ){
            currentPage = 1;
        }

        //查询目标页的记录数,开始查询的记录 ：start = (currentPage - 1)*rows
        int start = (currentPage - 1) * rows;
        List<Route> list = routeDao.findRoute(start,rows,cid , condition);



        //创建一个pagebean对象
        PageBean<Route> pageBean = new PageBean<Route>();
        pageBean.setRows(rows);
        pageBean.setCurrentPage(currentPage);
        pageBean.setList(list);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }

    @Override
    public Route findRouteDetail(String rid) {

        //查询路由路线
        Route route = routeDao.findRouteDetail(rid);
        //查询旅游路线的卖家
        Seller seller = sellerDao.findRouteSeller(route.getSid());
        //将查询的卖家信息封装到route中
        route.setSeller(seller);
        //查询旅游路线的图片
        List<RouteImg> routeImgs = routeImgDao.findRouteImg(route.getRid());
        //将查询到的旅游路线的图片封装到route中
        route.setRouteImgList(routeImgs);
        //查询旅游路线的类型
        Category category = categoryDao.findOneCategory(route.getCid());
        //将查询到的旅游路线的类型封装到route中
        route.setCategory(category);
        //查询收藏的次数
        int count = favoriteDao.findFavoriteCount(rid);
        //将查询到的收藏的次数封装到route中
        route.setCount(count);


        return route;
    }
}
