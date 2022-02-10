package com.yu.myblog.utils;

import lombok.Data;

import java.util.List;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.utils
 * @ClassName: ResultDao
 * @Author: 钟洪强
 * @Description: 返回结果集封装
 * @Date: 2022/1/26 9:53
 * @Version: 1.0
 */
@Data
public class ResultDao<v> {

    private int code;

    private String token;

    private String message;

    private List<v> data;

    private Object obj;

    private String url;

    private String imgUrl;

    private long total;
}
