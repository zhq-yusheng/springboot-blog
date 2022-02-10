package com.yu.myblog.service;

import com.yu.myblog.pojo.LeavingMessing;
import com.yu.myblog.pojo.User;
import com.yu.myblog.utils.ResultDao;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.service
 * @ClassName: UserService
 * @Author: 钟洪强
 * @Description: 用户业务接口
 * @Date: 2021/11/23 22:15
 * @Version: 1.0
 */
public interface UserService {
    ResultDao uploadTx(MultipartFile file, int uid); //上传头像
    ResultDao sendUpEmil(String email); // 成为up主发送邮箱信息
    ResultDao becomeUp(int uid); //成为up主
    ResultDao updateUser(User user); //修改用户信息
    ResultDao getUserById(int uid); //id获取用户信息
    ResultDao addMessage(LeavingMessing leavingMessing); //添加用户留言
    ResultDao getMessageByPage(int pageSize, int currPage); //查询用户留言
    ResultDao getUserUpDataById(int uid); //id获取用户信息






}
