package com.yu.myblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.pojo
 * @ClassName: MyblogView
 * @Author: 钟洪强
 * @Description: 我的博客数据视图实体类
 * @Date: 2022/1/25 11:06
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyblogView {

    private int bid;
    private int uid;
    private String title;
    private String blogType;
    private int popularity;
    private String titleimg;
    private String body;
    private int istoping;
    private Date createDatetime;
}
