package com.yu.myblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.pojo
 * @ClassName: QueryBlogView
 * @Author: 钟洪强
 * @Description: 查询博客视图
 * @Date: 2022/1/19 11:21
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryBlogView {

    private int bid;

    private String username;

    private String txUrl;

    private String blogType;

    private String title;

    private String titleHtml;

    private String titleimg;

    private String body;

    private String bodyHtml;

    private int popularity; // 点击量

    private String createDatetime;


}
