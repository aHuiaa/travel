package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findFavoriteCount(String rid) {
        String sql = "select count(*) from tab_favorite where rid = ?";
        Integer count = template.queryForObject(sql, Integer.class, rid);
        return count;
    }

    @Override
    public Favorite findFavorite(String rid, int uid) {
        String sql = "select * from tab_favorite where rid=? and uid=?";
        Favorite favorite = template.queryForObject(sql,new BeanPropertyRowMapper<>(Favorite.class),rid,uid);
        return favorite;
    }

    @Override
    public void favorite(String rid, String uid) {
        String sql = "insert into tab_favorite values(?,?,?)";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        template.update(sql,rid,sdf.format(date),uid);
    }

    @Override
    public void noFavorite(String rid, String uid) {
        String sql = "delete from tab_favorite where rid=? and uid=?";
        template.update(sql,rid,uid);
    }
}
