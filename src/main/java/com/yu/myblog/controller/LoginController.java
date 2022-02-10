package com.yu.myblog.controller;

import com.yu.myblog.pojo.User;
import com.yu.myblog.pojo.view.RegisterView;
import com.yu.myblog.service.LoginService;
import com.yu.myblog.utils.ResultDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.controller
 * @ClassName: LoginController
 * @Author: 钟洪强
 * @Description: 登陆注册接口
 * @Date: 2021/11/22 9:46
 * @Version: 1.0
 */

@RestController
@Log4j2
@CrossOrigin
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("register")
    public ResultDao register(@RequestBody RegisterView registerView){
        log.info("注册参数:{}",registerView);
        return loginService.register(registerView);
    }

    @PostMapping("/login")
    public ResultDao login(@RequestBody User user){
        log.info("登录参数:{}",user);
        return loginService.login(user);
    }

    @GetMapping("isEmail/{email}")
    public ResultDao isEmail(@PathVariable("email") String email){
        log.info("该email是否被注册:{}",email);
        return loginService.isEmail(email);
    }

    @GetMapping("sendEmail/{email}")
    public ResultDao sendEmail(@PathVariable("email") String email){
        log.info("发送邮件email号:{}",email);
        return loginService.sendEmail(email);
    }

}
