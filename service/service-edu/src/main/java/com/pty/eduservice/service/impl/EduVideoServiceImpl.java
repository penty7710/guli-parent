package com.pty.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pty.eduservice.entity.EduVideo;
import com.pty.eduservice.mapper.EduVideoMapper;
import com.pty.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    /**
     * 根据课程id删除小节
     * TODO 删除小节的时候还要删除对应的视频
     */
    @Override
    public void removeVideoByCourseId(String courseId) {
        baseMapper.delete(new QueryWrapper<EduVideo>().eq("course_id",courseId));
    }
}
