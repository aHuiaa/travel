package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 * 操作seller数据库的Dao
 */
public interface SellerDao {
    /**
     * 通过商家id查询商家
     * @param sid
     * @return
     */
    Seller findRouteSeller(int sid);
}
