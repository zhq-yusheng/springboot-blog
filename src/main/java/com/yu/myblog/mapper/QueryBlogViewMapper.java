package com.yu.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.myblog.pojo.QueryBlogView;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.mapper
 * @ClassName: QueryBlogViewMapper
 * @Author: 钟洪强
 * @Description: 查询博客视图mapper层
 * @Date: 2022/1/19 11:26
 * @Version: 1.0
 */
@Repository
public interface QueryBlogViewMapper extends BaseMapper<QueryBlogView> {
}
