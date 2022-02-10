package com.yu.myblog.email;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.email
 * @ClassName: Email
 * @Author: 钟洪强
 * @Description: 邮箱验证
 * @Date: 2021/11/23 19:33
 * @Version: 1.0
 */
@Component
@Log4j2
public class EmailChecking {

    @Autowired
    RedisTemplate redisTemplate;


    @Resource
    JavaMailSenderImpl mailSender;
    public int sendEmail(int code,String email){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try{
            MimeMessageHelper help =  new MimeMessageHelper(mimeMessage, true,"utf-8");
            String data = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
            String checking = ""; // 验证码
            Random random = new Random();
            // 随机生成验证码
            for (int i = 0; i < 4; i++) {
                int index = random.nextInt(data.length());
                checking+=data.charAt(index);
            }
            log.info("验证码为:{}",checking);
            if (code == 1){
                help.setSubject("www.zhonghq.top博客注册邮箱验证");

                help.setText("邮箱验证验证码为:"+checking+",验证码在5分钟后过期");
            }else{
                help.setSubject("www.zhonghq.top博客加入up主邮箱验证");

                help.setText("邮箱验证验证码为:"+checking+",验证码在5分钟后过期");
            }
            help.setTo(email);
            help.setFrom("2545763087@qq.com");
            redisTemplate.opsForValue().set(email,checking,60*5, TimeUnit.SECONDS);
            mailSender.send(mimeMessage);
            return 1;
        }catch (Exception e){
            System.out.println(e);
            log.error("发送邮件失败");
            return 0;
        }
    }
}
