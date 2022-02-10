package com.yu.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yu.myblog.email.EmailChecking;
import com.yu.myblog.mapper.UserMapper;
import com.yu.myblog.pojo.User;
import com.yu.myblog.pojo.view.RegisterView;
import com.yu.myblog.service.LoginService;
import com.yu.myblog.utils.ResultDao;
import com.yu.myblog.utils.TokenManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.service.impl
 * @ClassName: LoginServiceImpl
 * @Author: 钟洪强
 * @Description: 登陆注册接口实现
 * @Date: 2021/11/22 9:56
 * @Version: 1.0
 */
@Service
@Log4j2
public class LoginServiceImpl implements LoginService {


    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResultDao register(RegisterView registerView) {
        ResultDao resultDao = new ResultDao();
        Object data = redisTemplate.opsForValue().get(registerView.getEmail());
        if(data == null){
            resultDao.setCode(400);
            resultDao.setMessage("注册失败验证码过期请重新获取验证码");
            return resultDao;
        }
        ResultDao isRegister = isEmail(registerView.getEmail());
        if((int) isRegister.getCode() == 400){
            resultDao.setCode(400);
            resultDao.setMessage("邮箱已被注册");
        }
        User user = new User();
        user.setEmail(registerView.getEmail());
        user.setUsername(registerView.getUsername());
        user.setPwd(registerView.getPwd());
        int insert = userMapper.insert(user);

        if(insert == 1){
            resultDao.setCode(200);
            resultDao.setMessage("注册成功");
        }else{
            resultDao.setCode(400);
            resultDao.setMessage("注册失败");
        }
        return resultDao;
    }

    @Override
    public ResultDao login(User user) {
        ResultDao resultDao = new ResultDao();
        String email = user.getEmail();
        String pwd = user.getPwd();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email).eq("pwd",pwd);
        User usr = userMapper.selectOne(wrapper);
        if(usr == null){
            resultDao.setCode(400);
            resultDao.setMessage("账号或密码错误");
        }else{
            String token = TokenManager.createToken(usr.getUid() + "");
            //stringRedisTemplate.opsForValue().set(token,user.getUid() + "");
            redisTemplate.opsForValue().set(usr.getUid(), token,60 * 15,TimeUnit.SECONDS);
            Object o = redisTemplate.opsForValue().get(usr.getUid());
            log.info("redis存取uid:{}", o);
            resultDao.setCode(200);
            resultDao.setToken(token);
            resultDao.setObj(usr);
        }
        return resultDao;
    }

    @Autowired
    EmailChecking emailChecking;

    @Override
    public ResultDao sendEmail(String email) {
        ResultDao resultDao = new ResultDao();
        int code = emailChecking.sendEmail(1, email);
        if (code == 1){
            resultDao.setCode(200);
            resultDao.setMessage("邮件发送成功");
        }else{
            resultDao.setCode(400);
            resultDao.setMessage("邮件发送失败");
        }
        return resultDao;
    }

    @Override
    public ResultDao isEmail(String email) {
        ResultDao resultDao = new ResultDao();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email);
        User user = userMapper.selectOne(wrapper);
        if (user == null){
            resultDao.setCode(200);
            resultDao.setMessage("邮箱未注册");
        }else{
            resultDao.setCode(400);
            resultDao.setMessage("邮箱已注册");
        }
        return resultDao;
    }

}
