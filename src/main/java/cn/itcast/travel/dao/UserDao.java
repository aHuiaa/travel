package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * 操作User数据库的接口
 */
public interface UserDao {

    /**
     * 用户注册
     * @param user
     * @param uuid
     */
    void register(User user,String uuid);

    /**
     * 通过用户名查找用户，用于注册时查看是否存在此用户
     * @param username
     * @return
     */
    boolean findUserByUsername(String username);

    /**
     * 通过用户名查找用户的标识码，注册时生成的，用于激活用
     * @param username
     * @return
     */
    String findUsercodeByUsername(String username);

    /**
     * 更新用户的状态，激活后更改可登陆
     * @param username
     */
    void updateUserStatus(String username);

    /**
     * 用户登录查询认证
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password);
}
