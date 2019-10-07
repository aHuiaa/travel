package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import static cn.itcast.travel.util.JDBCUtils.getDataSource;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template =  new JdbcTemplate(getDataSource());

    /**
     * 按用户名查询用户
     * @param username
     * @return
     */
    @Override
    public boolean findUserByUsername(String username) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (DataAccessException e) {
        }
        if (user == null) {
            //没有查询到，返回false，说明可以注册
            return false;
        } else {
            //查询到了，返回true，说明不可以注册
            return true;
        }
    }

    @Override
    public String findUsercodeByUsername(String username) {
        //按照传入的用户名查询用户注册时的code
        try {
            String sql = "select code from tab_user where username = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
            return user.getCode();
        } catch (DataAccessException e) {
            return "";
        }
    }

    @Override
    public void updateUserStatus(String username) {
        String sql = "update tab_user set status = 'Y' where username = ?";
        template.update(sql,username);
    }

    @Override
    public User login(String username,String password) {
        User user = null;
        try {
            String sql ="select * from tab_user where username = ? and password = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;

    }

    /**
     * 注册用户
     * @param user
     */
    @Override
    public void register(User user,String uuid) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
         template.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail(),"N",uuid);
    }


}
