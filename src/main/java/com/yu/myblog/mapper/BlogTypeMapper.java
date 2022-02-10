package com.yu.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.myblog.pojo.BlogType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.mapper
 * @ClassName: BlogTypeMapper
 * @Author: 钟洪强
 * @Description: 博客类型mapper层
 * @Date: 2022/1/17 16:46
 * @Version: 1.0
 */
@Repository
public interface BlogTypeMapper extends BaseMapper<BlogType> {
    @Update("update blog_type set del=1 where btid = #{btid}")
    int deletedBlogType(@Param("btid") int btid);

    @Update("update blog_type set del=0 where btid = #{btid}")
    int recoveryBlogType(@Param("btid") int btid);
}
