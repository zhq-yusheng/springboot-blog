package com.yu.myblog.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.pojo
 * @ClassName: LeavingMessing
 * @Author: 钟洪强
 * @Description: 留言实体类
 * @Date: 2022/1/14 14:04
 * @Version: 1.0
 */
@Alias("leavingMessing")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeavingMessing {
    @TableId(type = IdType.AUTO)
    private Integer leid;

    private int uid;

    private String username;

    private String message;

    @TableField(fill = FieldFill.INSERT)
    private Date createDatetime;


}


