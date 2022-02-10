package com.yu.myblog.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.handler
 * @ClassName: BlogHandler
 * @Author: 钟洪强
 * @Description: 博客相关时间自动操作
 * @Date: 2022/1/13 10:20
 * @Version: 1.0
 */

@Component
@Slf4j
public class BlogHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("自动插入时间参数");
        //setFieldValByName(数据库字段名,创建一个时间对象，metaObject) 官网有最新写法可以借鉴
        this.setFieldValByName("createDatetime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
