package com.yu.myblog.controller;

import com.yu.myblog.pojo.Blog;
import com.yu.myblog.pojo.BlogType;
import com.yu.myblog.pojo.view.UserTxView;
import com.yu.myblog.service.BackstageService;
import com.yu.myblog.utils.ResultDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.controller
 * @ClassName: BackstageController
 * @Author: 钟洪强
 * @Description: 后台接口
 * @Date: 2022/1/12 15:23
 * @Version: 1.0
 */
@RestController
@Log4j2
@CrossOrigin
@RequestMapping("backstage")
public class BackstageController {

    @Autowired
    BackstageService backstageService;

    @PostMapping("/uploadImg")
    public ResultDao uploadTx(UserTxView userTxView){
        log.info("收到上传图片参数：{}",userTxView);
        return backstageService.uploadImage(userTxView.getFile(),userTxView.getUid());
    }

    @PostMapping("/addBlog")
    public ResultDao addBlog(@RequestBody Blog blog){
        log.info("收到添加博客参数：{}",blog);
        return backstageService.addBlog(blog);
    }
    @GetMapping("/deletedBlog/{bid}")
    public ResultDao deletedBlog(@PathVariable("bid") int bid){
        log.info("删除博客id：{}",bid);
        return backstageService.deletedBlog(bid);
    }

    @PostMapping("/addBlogType")
    public ResultDao addBlogType(@RequestBody BlogType blogType){
        log.info("收到添加博客类型参数：{}",blogType);
        return backstageService.addBlogType(blogType);
    }

    @GetMapping("getBlogType/{pageSize}/{currPage}")
    public ResultDao getBlogTypeByPage(@PathVariable("pageSize") int pageSize,
                                                @PathVariable("currPage") int currPage ){

        log.info("获取博客类型收到参数：currPage：{}，pageSize:{}",currPage, pageSize);
        return backstageService.queryBlogType(pageSize, currPage);
    }
    @PutMapping("updateBlogType/{btid}/{type}")
    public ResultDao updateBlogType(@PathVariable("btid") int btid,@PathVariable("type") int type ){
        log.info("修改博客类型收到参数：btid：{}，type:{}",btid, type);
        return backstageService.updateBlogType(btid, type);
    }


    @GetMapping("blogTopfive")
    public ResultDao blogTopFive(){
        return backstageService.blogTopFive();
    }

    @GetMapping("getSevenDayPopularity")
    public ResultDao getSevenDayPopularity(){
        return backstageService.getSevenDayPopularity();
    }

    @GetMapping("getMyBlogbyPage/{uid}/{currPage}/{pageSize}")
    public ResultDao getSevenDayPopularity(@PathVariable("uid")int uid,
                                                    @PathVariable("currPage")int currPage,
                                                    @PathVariable("pageSize")int pageSize){
        return backstageService.getMyBlogbyPage(uid, currPage, pageSize);
    }

    @GetMapping("getBlogCount")
    public ResultDao getBlogCount(){
        return backstageService.getBlogCount();
    }

    @GetMapping("getAuthorCount")
    public ResultDao getAuthorCount(){
        return backstageService.getAuthorCount();
    }

}
