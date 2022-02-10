package com.yu.myblog.pojo.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.pojo.view
 * @ClassName: UserTxView
 * @Author: 钟洪强
 * @Description: 用户头像上传参数接收对象
 * @Date: 2021/11/26 16:20
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTxView {
    private int uid;
    private MultipartFile file;
}
