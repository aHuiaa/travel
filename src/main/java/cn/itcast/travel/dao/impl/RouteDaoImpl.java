package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteDaoImpl implements RouteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public List<Route> findRoute(int start,int rows ,int cid ,String condition){
        String sql  = "select * from tab_route where 1=1 ";    //cid = ? limit ?,? ";
        StringBuilder sb  = new StringBuilder(sql);
        if (cid != 0){
            sb.append(" and cid ="+cid);
        }

        if (condition != null && !"".equals(condition) && !"null".equals(condition)){
           sb.append(" and rname like '%"+condition+"%' ");
        }
        sb.append(" limit ?,? ");
        sql = sb.toString();
        System.out.println("分页查询:"+sql);
        List<Route> routes = template.query(sql, new BeanPropertyRowMapper<>(Route.class),start, rows);
        return  routes;
    }

    @Override
    public int findTotalCount(int cid ,String condition) {
        String sql = "select count(*) from  tab_route where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        if (cid != 0){
            sb.append(" and cid ="+cid);
        }
        if (condition != null && !"".equals(condition) && !"null".equals(condition)){
            sb.append(" and rname like '%"+condition+"%' ");
        }
        sql = sb.toString();
        System.out.println("总数:"+sql);
        int totalCount = template.queryForObject(sql,Integer.class);
        return totalCount;
    }

    @Override
    public Route findRouteDetail(String rid) {
        String sql = "select * from tab_route where rid = ?";
        Route route = template.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class), rid);
        return route;
    }



}
