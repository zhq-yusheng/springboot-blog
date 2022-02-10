package com.yu.myblog.pojo.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.pojo.view
 * @ClassName: evenDayPopularityView
 * @Author: 钟洪强
 * @Description: 获取一周的点击量
 * @Date: 2022/1/24 15:36
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvenDayPopularityView {
    private int Popularity;
    private String date;
}
