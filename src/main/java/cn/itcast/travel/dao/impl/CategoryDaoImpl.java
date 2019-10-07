package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import static cn.itcast.travel.util.JDBCUtils.getDataSource;

public class CategoryDaoImpl implements CategoryDao {
    JdbcTemplate template = new JdbcTemplate(getDataSource());

    //查询导航栏
    public List<Category> findCategory(){
        String sql = "select * from tab_category order by cid";
        List<Category> categories = template.query(sql,new BeanPropertyRowMapper<>(Category.class));
        return  categories;
    }

    @Override
    public Category findOneCategory(int cid) {
        String sql = "select * from tab_category where cid = ?";
        Category category = template.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), cid);
        return category;
    }
}
