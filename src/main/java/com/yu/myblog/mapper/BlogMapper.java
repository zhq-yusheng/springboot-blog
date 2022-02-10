package com.yu.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.myblog.pojo.Blog;
import com.yu.myblog.pojo.view.BlogTopFiveView;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.mapper
 * @ClassName: BlogMapper
 * @Author: 钟洪强
 * @Description: 博客网站mapper层
 * @Date: 2022/1/13 10:24
 * @Version: 1.0
 */

@Repository
public interface BlogMapper extends BaseMapper<Blog> {
    List<BlogTopFiveView> getBlogTopFiv();
}
