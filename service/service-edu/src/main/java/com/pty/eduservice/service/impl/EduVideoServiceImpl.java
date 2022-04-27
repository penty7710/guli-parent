package com.pty.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pty.eduservice.client.VodClient;
import com.pty.eduservice.entity.EduVideo;
import com.pty.eduservice.mapper.EduVideoMapper;
import com.pty.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author pty
 * @since 2022-04-24
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient client;

    /**
     * 根据课程id删除小节
     */
    @Override
    public void removeVideoByCourseId(String courseId) {

        //查询出该课程下的所有视频
        List<EduVideo> videoList = baseMapper.selectList(new QueryWrapper<EduVideo>()
                .eq("course_id", courseId)
                .select("video_source_id"));

        List<String> videoIds = new ArrayList<>();
        for (EduVideo eduVideo : videoList) {
            if(!StringUtils.isEmpty(eduVideo.getVideoSourceId()))
                videoIds.add(eduVideo.getVideoSourceId());
        }

        if(videoIds.size() > 0){
            //根据多个视频id删除多个多个视频
            client.deleteBatch(videoIds);
        }


        baseMapper.delete(new QueryWrapper<EduVideo>().eq("course_id",courseId));
    }

}
