package com.yu.myblog.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.pojo
 * @ClassName: Blog
 * @Author: 钟洪强
 * @Description: 博客实体类
 * @Date: 2022/1/13 10:12
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @TableId(type = IdType.AUTO)
    private Integer bid;

    private int btid;  // 博客类型

    private int uid;

    private String title;

    private String titleHtml;

    private String titleImg;

    private String body;

    private String bodyHtml;

    @TableField(fill = FieldFill.INSERT)
    private Date createDatetime;

    @TableLogic  //逻辑删除注解
    private int deleted;

}
