package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * 操作RouteImg数据库的Dao
 */
public interface RouteImgDao {
    /**
     * 通过旅游线路的ID查询图片列表
     * @param rid
     * @return
     */
    List<RouteImg> findRouteImg(int rid);
}
