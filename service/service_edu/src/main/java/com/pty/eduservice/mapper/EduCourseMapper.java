package com.pty.eduservice.mapper;

import com.pty.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pty.eduservice.vo.CoursePublishVo;
import com.pty.eduservice.vo.CourseWebVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author pty
 * @since 2022-04-24
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    //根据课程id，获取发布课程的信息
    CoursePublishVo getPublishCourseInfo(String conrseId);

    //根据课程id，编写sql语句查询课程信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
