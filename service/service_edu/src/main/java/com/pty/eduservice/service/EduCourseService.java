package com.pty.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pty.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pty.eduservice.vo.CourseFrontVo;
import com.pty.eduservice.vo.CourseInfoVo;
import com.pty.eduservice.vo.CoursePublishVo;
import com.pty.eduservice.vo.CourseWebVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author pty
 * @since 2022-04-24
 */
public interface EduCourseService extends IService<EduCourse> {
    //添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> coursePage, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
