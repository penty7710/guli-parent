package com.pty.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.pty.servicebase.handler.GuliException;
import com.pty.vod.service.VodService;
import com.pty.vod.utils.InitVodClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * Created by 彭天怡 2022/4/26.
 */
@Slf4j
@Service
public class VodServiceImpl implements VodService {

    /**
     * 上传视频
     */
    @Override
    public String uploadVideoAly(MultipartFile file, String keyid, String keysecret) {
        try{

            //fileName：上传文件原始名称
            String fileName=file.getOriginalFilename();

            //title：上传之后显示的名称
            String title = fileName.substring(0,fileName.lastIndexOf("."));

            //上传文件输入流
            InputStream inputStream = file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest(keyid, keysecret, title, fileName, inputStream);

            String videoId = null;
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return  videoId;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //删除单个视频
    @Override
    public void removeAlyVideo(String keyid, String keysecret, String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(keyid, keysecret);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //向request中设置视频id

            request.setVideoIds(id);
            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw  new GuliException(20001,"删除视频失败");
        }
    }

    //一次性删除多个视频
    @Override
    public void removeMoreAlyVideo(List<String> videoIdList, String keyid, String keysecret) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(keyid, keysecret);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //将list集合转换成字符串并且元素之间使用逗号分隔
            String join = StringUtils.join(videoIdList.toArray(), ",");

            log.info(join);
            //向request中设置视频id
            request.setVideoIds(join);
            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw  new GuliException(20001,"删除视频失败");
        }
    }
}
