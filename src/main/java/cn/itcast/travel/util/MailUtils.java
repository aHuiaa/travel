package cn.itcast.travel.util;

import cn.itcast.travel.domain.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发邮件工具类
 */
public final class MailUtils {
    private static final String USER = "hooooooooy@163.com"; // 发件人称号，同邮箱地址
    private static final String PASSWORD = "123qweasdzxc"; // 如果是qq邮箱可以使客户端授权码，或者登录密码

    /**
     *
     * @param to 收件人邮箱
     * @param text 邮件正文
     * @param title 标题
     */
    /* 发送验证信息的邮件 */
    public static boolean sendMail(String to, String text, String title){
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.163.com");

            // 发件人的账号
            props.put("mail.user", USER);
            //发件人的密码
            props.put("mail.password", PASSWORD);

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            // 设置邮件标题
            message.setSubject(title);

            // 设置邮件的内容体
            message.setContent(text, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

//    public static void main(String[] args) throws Exception { // 做测试用
////        String uuid = UuidUtil.getUuid();
////        MailUtils.sendMail("1175088061@qq.com","你好，这是一封测试邮件，无需回复。<a href ='https://www.baidu.com'>点击</a>","激活邮件");
////        System.out.println("发送成功");
//        User user = new User();
//        user.setUsername("nihao");
//        user.setCode("12346as4d6a");
//        String uuid = user.getCode();
//        MailUtils.sendMail("179705833@qq.com","请<a href ='http://192.168.192.1/travel/activationServlet?username="+user.getUsername()+"&code=" + uuid + "'> 点击此处 </a>激活您的账号。","账号激活邮件");
//        System.out.println("激活邮件发送成功");
//    }



}