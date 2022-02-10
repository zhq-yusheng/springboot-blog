package com.yu.myblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.pojo
 * @ClassName: UserBlogDataView
 * @Author: 钟洪强
 * @Description: 用户注册up主后添加的一些博客信息
 * @Date: 2022/1/20 16:47
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBlogDataView {
    private int uid;
    private Date createUpDatetime;
    private int popularity;
    private int countBlog;
}
