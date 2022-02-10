package com.yu.myblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.pojo
 * @ClassName: BlogCountView
 * @Author: 钟洪强
 * @Description: 博客统计视图对象
 * @Date: 2022/2/3 21:13
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCountView {
    private String title;
    private String author;
    private int popularity;


}
