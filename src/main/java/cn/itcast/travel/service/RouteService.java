package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteService {

    PageBean<Route> findRouteByPage(int currentPage, int rows ,int cid ,String condition);

    Route findRouteDetail(String rid);
}
