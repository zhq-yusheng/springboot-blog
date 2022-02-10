package com.yu.myblog.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.pojo
 * @ClassName: BlogType
 * @Author: 钟洪强
 * @Description: 博客类型
 * @Date: 2022/1/17 16:30
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogType {
    @TableId(type = IdType.AUTO)
    private Integer btid;

    private String blogType;

    @TableField(fill = FieldFill.INSERT)
    private Date createDatetime;

    private int blogCount;

    private int del;
}
