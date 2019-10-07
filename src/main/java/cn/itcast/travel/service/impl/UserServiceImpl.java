package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;


public class UserServiceImpl implements UserService {
    UserDao dao = new UserDaoImpl();

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {
        boolean flag = dao.findUserByUsername(user.getUsername());
        if (flag){
            //如果个flag为true，说明查询到用户名，不能注册，返回false
            return false;
        }else{
            //如果flag为false，说明没有查询到用户名，可以注册
            String uuid = UuidUtil.getUuid();
            dao.register(user,uuid);
            MailUtils.sendMail(user.getEmail(),"请<a href ='http://192.168.190.1:8090/travel/user/activation?username="+user.getUsername()+"&code=" + uuid + "'> 点击此处 </a>激活您的账号，无需回复。","旅游网激活邮件");
            System.out.println("激活邮件发送成功");
            return true;
        }

    }

    @Override
    public boolean activation(String username, String code) {
        String usercode = dao.findUsercodeByUsername(username);
        //将返回的usercode和code比较，相同怎调用dao对用户的status进行更改
        if (usercode.equals(code)){
            dao.updateUserStatus(username);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public User login(String username,String password) {
       User user = dao.login(username,password);
        return  user;
    }
}
