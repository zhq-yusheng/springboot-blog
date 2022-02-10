package com.yu.myblog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.pojo
 * @ClassName: User
 * @Author: 钟洪强
 * @Description: 用户实体类
 * @Date: 2021/11/22 9:32
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Integer uid;
    private String username;
    private String pwd;
    private String email;
    private int isup;
    private Date createUpDatetime;
    private String imgurl;
    private int txlodcount;

}
