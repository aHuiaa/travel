package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

/**
 * 用户业务接口
 */
public interface UserService {
    /**
     * 用户注册接口
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 用户账号激活接口
     * @param username
     * @param code
     * @return
     */
    boolean activation(String username, String code);

    /**
     * 用户登录接口-》查询是否存在用户
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password);
}
