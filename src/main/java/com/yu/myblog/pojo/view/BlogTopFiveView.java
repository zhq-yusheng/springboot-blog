package com.yu.myblog.pojo.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.pojo.view
 * @ClassName: BlogTopFiveView
 * @Author: 钟洪强
 * @Description: 获取点击量前五的博客信息
 * @Date: 2022/1/24 14:30
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogTopFiveView {
    private String blogName;
    private int popularity;

}
