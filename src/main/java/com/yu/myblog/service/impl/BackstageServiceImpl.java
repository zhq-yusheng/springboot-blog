package com.yu.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.myblog.mapper.*;
import com.yu.myblog.pojo.*;
import com.yu.myblog.pojo.view.BlogTopFiveView;
import com.yu.myblog.pojo.view.EvenDayPopularityView;
import com.yu.myblog.service.BackstageService;
import com.yu.myblog.utils.ResultDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.service.impl
 * @ClassName: BackstageServiceImpl
 * @Author: 钟洪强
 * @Description:
 * @Date: 2022/1/12 15:27
 * @Version: 1.0
 */

@Service
@Log4j2
public class BackstageServiceImpl implements BackstageService {
    @Override
    public ResultDao uploadImage(MultipartFile file, int uid) {

        String filename = file.getOriginalFilename();
        String endStr = filename.substring(filename.length() - 4);
        String uuid = UUID.randomUUID().toString().substring(1, 8);
        String imgName = uid  + "is" + uuid + endStr;
        String savePath = "/usr/local/img";
        File newFile = new File(savePath);
        if(!newFile.exists()){
            newFile.mkdir();
        }

        try {
            file.transferTo(new File(newFile, imgName));
        }catch (IOException e){
            log.error("图片保存错误");
        }
        String imgUrl = "https://www.zhonghq.top:8080/images/"+imgName;

        ResultDao resultDao = new ResultDao();
        resultDao.setCode(200);
        resultDao.setMessage("图片上传成功");
        resultDao.setImgUrl(imgUrl);


        return resultDao;
    }

    @Autowired
    BlogMapper blogMapper;
    @Autowired
    BlogTypeMapper blogTypeMapper;

    @Override
    public ResultDao addBlog(Blog blog) {
        ResultDao resultDao = new ResultDao();
        //增加该类型的总文章数
        BlogType blogType = blogTypeMapper.selectById(blog.getBtid());
        blogType.setBlogCount(blogType.getBlogCount()+1);

        int insert = blogMapper.insert(blog);
        int update = blogTypeMapper.updateById(blogType);

        if (insert == 1 && update == 1){
            resultDao.setCode(200);
            resultDao.setMessage("博客发布成功");
        }else {
            resultDao.setCode(400);
            resultDao.setMessage("发布失败");
        }

        return resultDao;
    }

    @Override
    public ResultDao deletedBlog(int bid) {
        int delete = blogMapper.deleteById(bid);
        ResultDao resultDao = new ResultDao();
        if (delete == 1){
            resultDao.setCode(200);
            resultDao.setMessage("删除成功");
        }else {
            resultDao.setCode(400);
            resultDao.setMessage("删除失败");
        }
        return resultDao;
    }


    @Override
    public ResultDao addBlogType(BlogType blogType) {

        // 判断是否已经存在
        QueryWrapper<BlogType> wrapper = new QueryWrapper<>();
        wrapper.eq("blogType",blogType.getBlogType());
        BlogType queryBlogType = blogTypeMapper.selectOne(wrapper);

        ResultDao resultDao = new ResultDao();
        if(queryBlogType != null){
            resultDao.setCode(400);
            resultDao.setMessage("博客类型以存在");
            return resultDao;
        }
        int insert = blogTypeMapper.insert(blogType);
        if (insert == 1){
            resultDao.setCode(200);
            resultDao.setMessage("博客类型添加成功");
        }else {
            resultDao.setCode(400);
            resultDao.setMessage("添加失败,请联系管理员");
        }

        return resultDao;
    }

    @Override
    public ResultDao queryBlogType(int pageSize, int currPage) {
        QueryWrapper<BlogType> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("createDatetime");

        Page<BlogType> page = new Page<>(currPage, pageSize);
        blogTypeMapper.selectPage(page, wrapper);
        ResultDao resultDao = new ResultDao();
        resultDao.setCode(200);
        resultDao.setData(page.getRecords());
        resultDao.setTotal(page.getTotal());
        return resultDao;
    }

    @Override
    public ResultDao updateBlogType(int btid, int type) {
        ResultDao resultDao = new ResultDao();
        if(type == 1){
            int deletedBlogType = blogTypeMapper.deletedBlogType(btid);
            if (deletedBlogType == 1){
                resultDao.setCode(200);
                resultDao.setMessage("删除成功");
            }else{
                resultDao.setCode(400);
                resultDao.setMessage("删除失败");
            }
        }else{
            int recovery = blogTypeMapper.recoveryBlogType(btid);
            if (recovery == 1){
                resultDao.setCode(200);
                resultDao.setMessage("恢复成功");
            }else{
                resultDao.setCode(400);
                resultDao.setMessage("恢复失败");
            }
        }
        return resultDao;
    }

    @Override
    public ResultDao getAllBlogType() {
        ResultDao resultDao = new ResultDao();
        QueryWrapper<BlogType> wrapper = new QueryWrapper<>();
        wrapper.eq("del",0);
        List<BlogType> blogTypes = blogTypeMapper.selectList(wrapper);
        resultDao.setCode(200);
        resultDao.setData(blogTypes);
        return resultDao;
    }

    @Override
    public ResultDao blogTopFive() {
        ResultDao resultDao = new ResultDao();
        List<BlogTopFiveView> data = blogMapper.getBlogTopFiv();
        resultDao.setCode(200);
        resultDao.setData(data);
        return resultDao;
    }

    @Autowired
    PopularityMapper popularityMapper;

    @Override
    public ResultDao getSevenDayPopularity() {
        ResultDao resultDao = new ResultDao();
        List<EvenDayPopularityView> data = popularityMapper.getSevenDayPopularity();
        resultDao.setCode(200);
        resultDao.setData(data);
        return resultDao;
    }

    @Autowired
    MyblogViewMapper myblogViewMapper;

    @Override
    public ResultDao getMyBlogbyPage(int uid, int currPage, int pageSize) {
        ResultDao resultDao = new ResultDao();
        Page<MyblogView> page = new Page<>(currPage, pageSize);
        QueryWrapper<MyblogView> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        myblogViewMapper.selectPage(page, wrapper);
        resultDao.setCode(200);
        resultDao.setData(page.getRecords());
        resultDao.setTotal(page.getTotal());
        return resultDao;
    }

    @Autowired
    BlogCountViewMapper blogCountViewMapper;

    @Override
    public ResultDao getBlogCount() {
        ResultDao resultDao = new ResultDao();
        List<BlogCountView> blogCountViews = blogCountViewMapper.selectList(null);
        resultDao.setCode(200);
        resultDao.setData(blogCountViews);
        return resultDao;
    }

    @Autowired
    AuthorCountViewMapper authorCountViewMapper;

    @Override
    public ResultDao getAuthorCount() {
        ResultDao resultDao = new ResultDao();
        List<AuthorCountView> blogCountViews = authorCountViewMapper.selectList(null);
        resultDao.setCode(200);
        resultDao.setData(blogCountViews);
        return resultDao;
    }

    @Override
    public ResultDao toping(int bid, int flag) {
        Blog blog = blogMapper.selectById(bid);
        blog.setIstoping(flag);
        int update = blogMapper.updateById(blog);
        ResultDao<Object> resultDao = new ResultDao<>();
        if(update == 1){
            resultDao.setCode(200);
        }else{
            resultDao.setCode(400);
        }
        return resultDao;
    }
}
