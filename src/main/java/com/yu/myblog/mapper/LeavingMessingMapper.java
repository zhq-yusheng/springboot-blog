package com.yu.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.myblog.pojo.LeavingMessing;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.mapper
 * @ClassName: LeavingMessingMapper
 * @Author: 钟洪强
 * @Description: 留言mapper层
 * @Date: 2022/1/14 14:08
 * @Version: 1.0
 */

//@DS("") 配置多个数据源 这个来选择用哪个数据源 详情使用百度 https://www.cnblogs.com/zheng-hong-bo/p/15580805.html
@Repository
public interface LeavingMessingMapper extends BaseMapper<LeavingMessing> {
}
