package com.pty.eduservice.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.pty.commonutils.R;
import com.pty.eduservice.client.VodClient;
import com.pty.eduservice.entity.EduVideo;
import com.pty.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author pty
 * @since 2022-04-24
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    //注入删除阿里云视频的接口
    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }


    //删除小节,同时删除视频
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        //根据小节id获取视频id
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)){
            //根据视频id，远程调用eduvod 实现删除功能
            vodClient.removeAlyVideo(videoSourceId);
        }
        videoService.removeById(id);
        return R.ok();
    }

}

