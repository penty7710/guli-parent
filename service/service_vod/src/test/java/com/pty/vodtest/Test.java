package com.pty.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;

import java.util.List;

/**
 * Created by 彭天怡 2022/4/26.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        String  accessKeyId = "LTAI5tMPmnRuSjU3wmEC13TH";
        String  accessKeySecret = "60w1Stbt3qSUw6ABilXLeCvSWuFw10";
        //上传之后文件显示的名称
        String  title = "6 - What If I Want to Move Faster.mp4";
        //本地文件的路径和名称
        String  fileName = "G:\\6 - What If I Want to Move Faster.mp4";
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    /**
     * 根据视频id获取播放地址
     */
    public static void getPlayUrl() throws ClientException {
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tMPmnRuSjU3wmEC13TH", "60w1Stbt3qSUw6ABilXLeCvSWuFw10");


        //创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        //向request里面设置视频id
        request.setVideoId("7b34ce3205aa4b9e8aa2e6888e2391be");

        //调用初始化对象里面的方法，传递request，获取数据
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for(GetPlayInfoResponse.PlayInfo playInfo:playInfoList){
            System.out.print("PlayInfo.PlayURL = "+playInfo.getPlayURL()+"\n" );
        }
        //Base信息
        System.out.println("VideoBase.Title = "+response.getVideoBase().getTitle()+"\n");
    }

    // 根据视频iD获取视频播放凭证
    public static void getPlayAuth() throws Exception{
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tMPmnRuSjU3wmEC13TH", "60w1Stbt3qSUw6ABilXLeCvSWuFw10");
        //创建获取视频凭证request和response
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        //向request设置视频id
        request.setVideoId("fb08a22b647f42599c1cd74fe0693b4a");

        //调用初始化对象的方法得到凭证
        response = client.getAcsResponse(request);
        System.out.println("playAuth:"+response.getPlayAuth());
    }
}
