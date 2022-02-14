package com.yu.myblog.service;

import com.yu.myblog.pojo.Blog;
import com.yu.myblog.pojo.BlogType;
import com.yu.myblog.utils.ResultDao;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.service
 * @ClassName: BackstageService
 * @Author: 钟洪强
 * @Description: 后台接口service
 * @Date: 2022/1/12 15:25
 * @Version: 1.0
 */

public interface BackstageService {
    ResultDao uploadImage(MultipartFile file, int uid); // 上传图片
    ResultDao addBlog(Blog blog); // 添加博客
    ResultDao deletedBlog(int bid); // 删除博客
    ResultDao addBlogType(BlogType blog);  // 添加博客类型
    ResultDao queryBlogType(int pageSize, int currPage); // 分页查询博客类型
    ResultDao updateBlogType(int btid, int type); // 删除恢复博客类型
    ResultDao getAllBlogType(); // 查询全部的博客类型
    ResultDao blogTopFive(); // 查询点击量前五的blog
    ResultDao getSevenDayPopularity(); // 查询前七天的点击量
    ResultDao getMyBlogbyPage(int uid,int currPage,int pageSize); //根据id查询用户博客信息
    ResultDao getBlogCount();
    ResultDao getAuthorCount();
    ResultDao toping(int bid,int flag); // 置顶方法
}
