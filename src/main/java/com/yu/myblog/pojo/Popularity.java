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
 * @ClassName: Popularity
 * @Author: 钟洪强
 * @Description: 点击量表
 * @Date: 2022/1/24 13:56
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Popularity {

    @TableId(type = IdType.AUTO)
    private Integer poid;
    private int bid;
    @TableField(fill = FieldFill.INSERT)
    private Date createDatetime;
}
