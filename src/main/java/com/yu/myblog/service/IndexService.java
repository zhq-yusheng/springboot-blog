package com.yu.myblog.service;

import com.yu.myblog.utils.ResultDao;

import java.util.Map;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.service
 * @ClassName: InexdexService
 * @Author: 钟洪强
 * @Description: 不要登录token就能访问的接口
 * @Date: 2022/1/19 14:19
 * @Version: 1.0
 */
public interface IndexService {

    ResultDao likeQueryBlogByPage(String title, int pageSize, int currPage);
    ResultDao getBlogByPage(int pageSize, int currPage);
    ResultDao getBlogByblogTypeByPage(int pageSize, int currPage, int btid);
    ResultDao getBlogById(int id);
    void addPopularity(int bid);
    ResultDao getSiteInformation();

}
