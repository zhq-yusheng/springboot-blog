package com.yu.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.myblog.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.mapper
 * @ClassName: UserMapper
 * @Author: 钟洪强
 * @Description: 用户mapper接口
 * @Date: 2021/11/22 9:43
 * @Version: 1.0
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
