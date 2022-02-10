package com.yu.myblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.pojo
 * @ClassName: SiteInformationView
 * @Author: 钟洪强
 * @Description: 站点信息
 * @Date: 2022/1/21 9:39
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteInformationView {
    private int blogCount; // 博客数
    private int blogTypeCount; // 博客类型数
    private int upCount;  // up主数
    private int leavingCount; // 留言数
    private int popCount; // 页面总点击量
}
