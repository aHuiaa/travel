package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * 操作Route数据库的Dao
 */
public interface RouteDao {
    /**
     * 查询旅游线路
     * @param currentCount
     * @param rows
     * @param cid
     * @param condition
     * @return
     */
    List<Route> findRoute(int currentCount, int rows,int cid , String condition);

    /**
     * 查询旅游线路的总数
     * @param cid
     * @param condition
     * @return
     */
    int findTotalCount( int cid ,String condition);

    /**
     * 查询旅游线路的详情
     * @param rid
     * @return
     */
    Route findRouteDetail(String rid);

}
