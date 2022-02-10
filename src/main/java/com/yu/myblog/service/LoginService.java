package com.yu.myblog.service;

import com.yu.myblog.pojo.User;
import com.yu.myblog.pojo.view.RegisterView;
import com.yu.myblog.utils.ResultDao;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.service
 * @ClassName: LoginSerdvice
 * @Author: 钟洪强
 * @Description: 登陆注册业务接口
 * @Date: 2021/11/22 9:53
 * @Version: 1.0
 */
public interface LoginService {
    ResultDao register(RegisterView registerView);
    ResultDao login(User user);
    ResultDao sendEmail(String email);
    ResultDao isEmail(String email); // 判断邮箱是否注册过
}
