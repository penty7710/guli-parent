package com.pty.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.pty.vod.service.VodService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Created by 彭天怡 2022/4/26.
 */
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
}
