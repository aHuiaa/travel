package cn.itcast.travel.dao;
import cn.itcast.travel.domain.Favorite;

/**
 * 操作Favorite数据库的Dao
 */
public interface FavoriteDao {
    /**
     * 查询收藏的总数量
     * @param rid 旅游路线的id
     * @return
     */
    int findFavoriteCount(String rid);

    /**
     * 查询用户是否收藏了该路线
     * @param rid 旅游路线的id
     * @param uid 用户的id
     * @return
     */
    Favorite findFavorite(String rid, int uid);

    /**
     * 用户添加收藏
     * @param rid
     * @param uid
     */
    void favorite(String rid, String uid);

    /**
     * 用户取消收藏
     * @param rid
     * @param uid
     */
    void noFavorite(String rid, String uid);
}
