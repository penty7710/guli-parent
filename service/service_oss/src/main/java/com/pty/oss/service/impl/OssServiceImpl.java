package com.pty.oss.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.pty.oss.service.OssService;
import com.pty.oss.utils.ConstantPropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.UUID;

/**
 * 上传头像到oss
 * Created by 彭天怡 2022/4/21.
 */
@Slf4j
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            //获取上传文件的输入流
            InputStream inputStream = file.getInputStream();

            //生成uuid，避免文件名重复
            String uuid = UUID.randomUUID().toString().replace("-","");

            //文件保存路径
            //这里不能使用/ 或 \ 开头
            String fileName = uuid+file.getOriginalFilename();

            //把文件按照日期进行分类
            //获取当前日期的年份和月份
            DateTime date = DateUtil.date();
            String datePath = DateUtil.format(date, "yyyy/MM/dd");
            fileName = datePath+"/"+fileName;


            //调用oss方法实现上传
            //第一个参数：bucket的名称
            //第二个参数：上传到oss文件路径
            //第三个参数：上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            //关闭连接
            ossClient.shutdown();

            //返回上传之后的文件访问链接
            //链接格式：https://buckname.endpoint/filename
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            log.info("上传图片");
            return url;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
