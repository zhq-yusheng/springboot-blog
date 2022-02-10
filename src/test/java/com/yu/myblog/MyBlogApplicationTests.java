package com.yu.myblog;

import com.auth0.jwt.interfaces.Claim;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yu.myblog.email.EmailChecking;
import com.yu.myblog.mapper.PopularityMapper;
import com.yu.myblog.mapper.UserMapper;
import com.yu.myblog.pojo.view.EvenDayPopularityView;
import com.yu.myblog.service.BackstageService;
import com.yu.myblog.service.UserService;
import com.yu.myblog.utils.ResultDao;
import com.yu.myblog.utils.TokenManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class MyBlogApplicationTests {

    @Autowired
    EmailChecking emailChecking;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    PopularityMapper popularityMapper;

    @Autowired
    BackstageService backstageService;
    @Test
    void contextLoads() throws IOException, InterruptedException {
       /* int i = emailChecking.sendEmail(1, "663855369@qq.com");
        System.out.println(i);*/
        /*Object data = redisTemplate.opsForValue().get("663855369@qq.com");
        System.out.println(data);*/
        /*List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);*/
        /*Map<String, Object> page = userService.getMessageByPage(5, 1);
        System.out.println(page);*/

       /* List<EvenDayPopularityView> sevenDayPopularity = popularityMapper.getSevenDayPopularity();
        System.out.println(sevenDayPopularity);*/
       /* String token = TokenManager.createToken(1 + "");
        System.out.println(token);
        //String s  = TokenManager.userId(token);
        String str = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJDbGllbnQiLCJpc3MiOiJTZXJ2aWNlIiwiZXhwIjoxNjQzNjQwMDIxLCJ1c2VySWQiOiIxIiwiaWF0IjoxNjQzNjQwMDIwfQ.H6I_SMRdz3Xj23I38y5bBhtTJVuFf3KvI8RrYFz8c3Y";
        *//*String id = TokenManager.userId(str);
        System.out.println(id);*//*

        *//*sun.misc.BASE64Decoder decode = new sun.misc.BASE64Decoder();
        String json = new String(decode.decodeBuffer(str));*//*
        Object o = redisTemplate.opsForValue().get("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJDbGllbnQiLCJpc3MiOiJTZXJ2aWNlIiwiZXhwIjoxNjQzNjQzOTkwLCJ1c2VySWQiOiI0IiwiaWF0IjoxNjQzNjQzOTg5fQ.LryGpzF9uX8M90RsRECZ-_CNFf8mAUCp9WGLBTwrnCk");
        System.out.println(o.toString());*/

        ResultDao blogCount = backstageService.getAuthorCount();
        System.out.println(blogCount);

    }

}
