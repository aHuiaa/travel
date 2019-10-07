package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class CategoryServiceImpl  implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findCategory() {
        //1.首先从redis中查询
        //获取redis客户端
        Jedis jedis = JedisUtil.getJedis();
//        Set<String> category = jedis.zrange("category", 0, -1);
        //从redis中查询所有key为category的数据
        Set<Tuple> category = jedis.zrangeWithScores("category", 0, -1);

        List<Category> categories = null;
        //判断查询的集合是否为空
        if (category == null || category.size() == 0){
            //如果redis中的记录为空，则从数据库中查询数据
           // System.out.println("从数据库中查询的");
             categories = categoryDao.findCategory();
             //将数据库查询的数据，存入radis中
            for (Category c:categories) {
                jedis.zadd("category",c.getCid(),c.getCname());
            }
        }else {
            //如果redis中的记录不为空，则从redis中查询数据
            //System.out.println("从radis中查询的");
            categories = new ArrayList<Category>();
            //从redis查询出来的数据是set集合，要将其数据转为list集合
            //tuple类型包含了存储时的分数score，和值
            for (Tuple tuple :category){
                Category cg = new Category();
                cg.setCid((int) tuple.getScore());
                cg.setCname(tuple.getElement());
                categories.add(cg);
                //System.out.println(cg);
            }
          //  System.out.println(categories);
        }

        return categories;
    }
}

