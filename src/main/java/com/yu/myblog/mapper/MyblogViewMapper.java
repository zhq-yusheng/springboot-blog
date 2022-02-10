package com.yu.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.myblog.pojo.MyblogView;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.mapper
 * @ClassName: MyblogViewMapper
 * @Author: 钟洪强
 * @Description: 我的博客信息视图mapper层
 * @Date: 2022/1/25 11:09
 * @Version: 1.0
 */
@Repository
public interface MyblogViewMapper extends BaseMapper<MyblogView> {
}
