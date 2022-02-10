package com.yu.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.myblog.mapper.BlogMapper;
import com.yu.myblog.mapper.PopularityMapper;
import com.yu.myblog.mapper.QueryBlogViewMapper;
import com.yu.myblog.mapper.SiteInformationViewMapper;
import com.yu.myblog.pojo.Popularity;
import com.yu.myblog.pojo.QueryBlogView;
import com.yu.myblog.pojo.SiteInformationView;
import com.yu.myblog.service.IndexService;
import com.yu.myblog.utils.ResultDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.service.impl
 * @ClassName: IndexServiceImpl
 * @Author: 钟洪强
 * @Description: 不要登录能访问的接口实现
 * @Date: 2022/1/19 14:21
 * @Version: 1.0
 */
@Service
@Slf4j
public class IndexServiceImpl implements IndexService {

    @Autowired
    QueryBlogViewMapper queryBlogViewMapper;

    @Override
    public ResultDao likeQueryBlogByPage(String title, int pageSize, int currPage) {
        QueryWrapper<QueryBlogView> wrapper = new QueryWrapper<>();
        wrapper.like("title",title);
        Page<QueryBlogView> page = new Page<>(currPage, pageSize);
        queryBlogViewMapper.selectPage(page, wrapper);

        ResultDao resultDao = new ResultDao();

        resultDao.setCode(200);
        resultDao.setData(page.getRecords());
        resultDao.setTotal(page.getTotal());

        return resultDao;
    }

    @Override
    public ResultDao getBlogByPage(int pageSize, int currPage) {
        Page<QueryBlogView> page = new Page<>(currPage, pageSize);
        queryBlogViewMapper.selectPage(page, null);
        ResultDao resultDao = new ResultDao();

        resultDao.setCode(200);
        resultDao.setData(page.getRecords());
        resultDao.setTotal(page.getTotal());

        return resultDao;
    }

    @Override
    public ResultDao getBlogByblogTypeByPage(int pageSize, int currPage, int btid) {
        QueryWrapper<QueryBlogView> wrapper = new QueryWrapper<>();
        wrapper.eq("btid", btid);
        Page<QueryBlogView> page = new Page<>(currPage, pageSize);
        queryBlogViewMapper.selectPage(page, wrapper);
        ResultDao resultDao = new ResultDao();

        resultDao.setCode(200);
        resultDao.setData(page.getRecords());
        resultDao.setTotal(page.getTotal());

        return resultDao;
    }

    @Override
    public ResultDao getBlogById(int id) {
        QueryWrapper<QueryBlogView> wrapper = new QueryWrapper<>();
        wrapper.eq("bid", id);
        QueryBlogView queryBlogView = queryBlogViewMapper.selectOne(wrapper);
        ResultDao resultDao = new ResultDao();
        this.addPopularity(id);
        resultDao.setCode(200);
        resultDao.setObj(queryBlogView);
        return resultDao;
    }

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    PopularityMapper popularityMapper;
    @Override
    public void addPopularity(int bid) {
        Popularity popularity = new Popularity();
        popularity.setBid(bid);
        int insert = popularityMapper.insert(popularity);
        if(insert == 1){
            log.info("博客id为{}的博客增加了1人气点",bid);
        }

    }

    @Autowired
    SiteInformationViewMapper siteInformationViewMapper;

    @Override
    public ResultDao getSiteInformation() {
        SiteInformationView siteInformationView = siteInformationViewMapper.selectOne(null);
        ResultDao resultDao = new ResultDao();
        resultDao.setCode(200);
        resultDao.setObj(siteInformationView);
        return resultDao;
    }
}
