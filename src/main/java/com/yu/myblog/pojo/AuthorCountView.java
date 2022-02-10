package com.yu.myblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.pojo
 * @ClassName: AuthorCountView
 * @Author: 钟洪强
 * @Description:
 * @Date: 2022/2/3 22:00
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorCountView {
    private String username;
    private int bcounts;
    private int popularity;
}
