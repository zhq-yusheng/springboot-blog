package com.yu.myblog.pojo.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.pojo.view
 * @ClassName: RegisterView
 * @Author: 钟洪强
 * @Description: 注册参数接收对象
 * @Date: 2021/11/24 19:49
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterView {
    private String username;
    private String pwd;
    private String email;
    private String emailPwd;
}
