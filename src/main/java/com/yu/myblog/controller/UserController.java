package com.yu.myblog.controller;

import com.yu.myblog.pojo.LeavingMessing;
import com.yu.myblog.pojo.User;
import com.yu.myblog.pojo.view.UserTxView;
import com.yu.myblog.service.UserService;
import com.yu.myblog.utils.ResultDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.controller
 * @ClassName: UserController
 * @Author: 钟洪强
 * @Description: 用户controller接口
 * @Date: 2021/11/24 10:24
 * @Version: 1.0
 */

@Log4j2
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("sendUpEmil/{email}")
    public ResultDao sendUpEmil(@PathVariable("email") String email){
        return userService.sendUpEmil(email);
    }

    @PostMapping("/uploadTx")
    public ResultDao uploadTx(UserTxView userTxView){
        log.info("收到参数{}",userTxView);
       return userService.uploadTx(userTxView.getFile(),userTxView.getUid());
    }

    @GetMapping("becomeUp/{uid}")
    public ResultDao becomeUp(@PathVariable("uid") int uid){
        return userService.becomeUp(uid);
    }

    @PostMapping("/updateUser")
    public ResultDao updateUser(@RequestBody User User){
        log.info("修改用户信息：{}",User);
        return userService.updateUser(User);
    }

    @GetMapping("getUserById/{uid}")
    public ResultDao getUserById(@PathVariable("uid") int uid){
        return userService.getUserById(uid);
    }


    @PostMapping("/addMessage")
    public ResultDao addMessage(@RequestBody LeavingMessing leavingMessing){
        log.info("修改用户信息：{}",leavingMessing);
        return userService.addMessage(leavingMessing);
    }

    @GetMapping("getUserUpDataById/{uid}")
    public ResultDao getUserUpDataById(@PathVariable("uid") int uid){
        return userService.getUserUpDataById(uid);
    }
}
