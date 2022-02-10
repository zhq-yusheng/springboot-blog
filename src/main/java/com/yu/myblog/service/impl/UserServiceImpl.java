package com.yu.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.myblog.email.EmailChecking;
import com.yu.myblog.mapper.LeavingMessingMapper;
import com.yu.myblog.mapper.UserBlogDataViewMapper;
import com.yu.myblog.mapper.UserMapper;
import com.yu.myblog.pojo.LeavingMessing;
import com.yu.myblog.pojo.User;
import com.yu.myblog.pojo.UserBlogDataView;
import com.yu.myblog.service.UserService;
import com.yu.myblog.utils.ResultDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.service.impl
 * @ClassName: UserServiceImpl
 * @Author: 钟洪强
 * @Description: 用户接口实现类
 * @Date: 2021/11/23 22:20
 * @Version: 1.0
 */
@Log4j2
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public ResultDao uploadTx(MultipartFile file, int uid) {
        User user = userMapper.selectById(uid);

        String filename = file.getOriginalFilename();
        String endStr = filename.substring(filename.length() - 4); // 获取文件上传的后缀
        String txName = uid +  "" + user.getTxlodcount() + "tx" + endStr;
        String savePath = "/usr/local/img";
        File newFile = new File(savePath);
        if(!newFile.exists()){ // 判断文件路径是否存在 如果不存在就创建
            newFile.mkdir();
        }

        try {
            file.transferTo(new File(newFile, txName));
        }catch (IOException e){
            log.error("头像保存错误");
        }
        String txUrl = "https://www.zhonghq.top:8080/images/"+txName;
        String imgurl = user.getImgurl();
        if(imgurl != null){
            String[] urlList = imgurl.split("/");
            String urlTxName = urlList[urlList.length - 1];
            File deletedTXFile = new File("/usr/local/img", urlTxName);
            if(deletedTXFile.exists()){
                deletedTXFile.delete();
            }
        }

        user.setTxlodcount(user.getTxlodcount() + 1);
        user.setImgurl(txUrl);
        userMapper.updateById(user);

        ResultDao resultDao = new ResultDao();

        resultDao.setCode(200);
        resultDao.setMessage("头像上传成功");
        resultDao.setImgUrl(txUrl);


        return resultDao;
    }

    @Autowired
    EmailChecking emailChecking;

    @Override
    public ResultDao sendUpEmil(String email) {
        ResultDao resultDao = new ResultDao();
        int code = emailChecking.sendEmail(2, email);
        if (code == 1){
            resultDao.setCode(200);
            resultDao.setMessage("邮件发送成功");
        }else{
            resultDao.setCode(400);
            resultDao.setMessage("邮件发送失败");
        }
        return resultDao;
    }

    @Override
    public ResultDao becomeUp(int uid) {
        ResultDao resultDao = new ResultDao();
        User user = userMapper.selectById(uid);
        user.setIsup(1);
        user.setCreateUpDatetime(new Date());
        int update = userMapper.updateById(user);
        if(update == 1){
            resultDao.setCode(200);
            resultDao.setMessage("成为up主成功，开始你的文章发布吧");
        }else{
            resultDao.setCode(400);
            resultDao.setMessage("开通失败");
        }
        return resultDao;
    }

    @Override
    public ResultDao updateUser(User user) {
        int update = userMapper.updateById(user);
        ResultDao resultDao = new ResultDao();
        if (update == 1){
            resultDao.setCode(200);
            resultDao.setMessage("修改成功");
        }else{
            resultDao.setCode(400);
            resultDao.setMessage("修改失败");
        }
        return resultDao;
    }

    @Override
    public ResultDao getUserById(int uid) {
        User user = userMapper.selectById(uid);
        ResultDao resultDao = new ResultDao();
        if (user == null){
            resultDao.setCode(400);
        }else{
            resultDao.setCode(200);
            resultDao.setObj(user);
        }
        return resultDao;
    }

    @Autowired
    LeavingMessingMapper leavingMessingMapper;

    @Override
    public ResultDao addMessage(LeavingMessing leavingMessing) {
        ResultDao resultDao = new ResultDao();
        int insert = leavingMessingMapper.insert(leavingMessing);
        if(insert == 1){
            resultDao.setCode(200);
            resultDao.setMessage("留言成功");
        }else{
            resultDao.setCode(400);
            resultDao.setMessage("留言失败,请联系管理人员");
        }
        return resultDao;
    }

    @Override
    public ResultDao getMessageByPage(int pageSize, int currPage) {
        Page<LeavingMessing> leavingMessingPage = new Page<>(currPage,pageSize);
        QueryWrapper<LeavingMessing> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("createDatetime");
        leavingMessingMapper.selectPage(leavingMessingPage,wrapper);
        ResultDao resultDao = new ResultDao();
        resultDao.setCode(200);
        resultDao.setData(leavingMessingPage.getRecords());
        resultDao.setTotal(leavingMessingPage.getTotal());
        return resultDao;
    }

    @Autowired
    UserBlogDataViewMapper userBlogDataViewMapper;

    @Override
    public ResultDao getUserUpDataById(int uid) {

        QueryWrapper<UserBlogDataView> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);

        UserBlogDataView dataView = userBlogDataViewMapper.selectOne(wrapper);

        ResultDao resultDao = new ResultDao();
        resultDao.setCode(200);
        resultDao.setObj(dataView);
        return resultDao;
    }


}
