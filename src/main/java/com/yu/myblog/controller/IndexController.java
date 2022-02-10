package com.yu.myblog.controller;

import com.yu.myblog.service.BackstageService;
import com.yu.myblog.service.IndexService;
import com.yu.myblog.service.UserService;
import com.yu.myblog.utils.ResultDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.controller
 * @ClassName: IndexController
 * @Author: 钟洪强
 * @Description: 不需要token就能访问的接口
 * @Date: 2022/1/19 14:17
 * @Version: 1.0
 */
@RestController
@RequestMapping("index")
@Slf4j
@CrossOrigin
public class IndexController {

    @Autowired
    IndexService indexService;

    @Autowired
    UserService userService;

    @Autowired
    BackstageService backstageService;


    @GetMapping("getlevingMessage/{pageSize}/{currPage}")
    public ResultDao getUserById(@PathVariable("pageSize") int pageSize, @PathVariable("currPage") int currPage ){
        log.info("收到参数：currPage：{}，pageSize:{}",currPage, pageSize);
        return userService.getMessageByPage(pageSize, currPage);
    }

    @GetMapping("getAllBlogType")
    public ResultDao getAllBlogType(){
        return backstageService.getAllBlogType();
    }

    @GetMapping("search/{title}")
    public ResultDao isEmail(@PathVariable("title") String title){
        log.info("模糊查询:{}",title);
        return indexService.likeQueryBlogByPage(title, 2, 1);
    }

    @GetMapping("searchBlogByPage/{title}/{pageSize}/{currPage}")
    public ResultDao searchBlogByPage(@PathVariable("title") String title,
                                               @PathVariable("pageSize") int pageSize,
                                               @PathVariable("currPage") int currPage ){
        log.info("搜索翻页收到参数：currPage：{}，pageSize:{},title:{}",currPage, pageSize,title);
        return indexService.likeQueryBlogByPage(title, pageSize, currPage);
    }

    @GetMapping("getBlogByPage/{pageSize}/{currPage}")
    public ResultDao getBlogByPage(@PathVariable("pageSize") int pageSize,@PathVariable("currPage") int currPage ){
        log.info("默认获取博客信息收到参数：currPage：{}，pageSize:{}",currPage, pageSize);
        return indexService.getBlogByPage(pageSize, currPage);
    }

    @GetMapping("getBlogByblogTypeByPage/{pageSize}/{currPage}/{btid}")
    public ResultDao getBlogByblogTypeByPage(@PathVariable("pageSize") int pageSize,
                                                      @PathVariable("currPage") int currPage,
                                                      @PathVariable("btid") int btid ){
        log.info("通过博客类型获取博客收到参数：currPage：{}，pageSize:{},博客类型id:{}",currPage, pageSize,btid);
        return indexService.getBlogByblogTypeByPage(pageSize, currPage, btid);
    }

    @GetMapping("getBlogById/{id}")
    public ResultDao getBlogById(@PathVariable("id") int id){
        log.info("通过id获取博客内容收到id:{}",id);
        return indexService.getBlogById(id);
    }

    @GetMapping("getSiteInformation")
    public ResultDao getSiteInformation(){
        log.info("调用获取站点信息接口");
        return indexService.getSiteInformation();
    }

    @GetMapping("getUserUpDataById/{uid}")
    public ResultDao getUserUpDataById(@PathVariable("uid") int uid){
        return userService.getUserUpDataById(uid);
    }

}
