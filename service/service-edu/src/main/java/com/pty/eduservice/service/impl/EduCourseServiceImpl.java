package com.pty.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pty.eduservice.entity.EduChapter;
import com.pty.eduservice.entity.EduCourse;
import com.pty.eduservice.entity.EduCourseDescription;
import com.pty.eduservice.entity.EduVideo;
import com.pty.eduservice.mapper.EduCourseMapper;
import com.pty.eduservice.service.EduChapterService;
import com.pty.eduservice.service.EduCourseDescriptionService;
import com.pty.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pty.eduservice.service.EduVideoService;
import com.pty.eduservice.vo.CourseInfoVo;
import com.pty.eduservice.vo.CoursePublishVo;
import com.pty.servicebase.handler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author pty
 * @since 2022-04-24
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    //注入小节service
    @Autowired
    private EduVideoService eduVideoService;

    //注入章节service
    @Autowired
    private EduChapterService eduChapterService;


    /**
     * 添加课程基本信息
     */
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1 向课程表添加课程基本信息
        //CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);

        int insert = baseMapper.insert(eduCourse);
        if(insert <0){
            throw  new GuliException(20001,"添加课程失败");
        }
        //获取添加之后课程id
        String cid = eduCourse.getId();

        //2 向课程简介表添加课程简介
        //edu_course_description
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //获取添加之后课程id
        courseDescription.setId(cid);
        eduCourseDescriptionService.save(courseDescription);

        return cid;
    }

    /**
     * 查询课程的基本信息
     */
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1 查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);

        //2 查询描述表
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    /**
     * 修改课程信息
     */
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1 修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if(update == 0) {
            throw new GuliException(20001,"修改课程信息失败");
        }

        //2 修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.updateById(description);
    }

    /**
     * 获取发布课程的信息
     * @param id
     * @return
     */
    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        return baseMapper.getPublishCourseInfo(id);
    }

    //删除课程
    @Transactional
    @Override
    public void removeCourse(String courseId) {
        System.out.println(courseId);

        //1.根据课程id删除小节
        eduVideoService.removeVideoByCourseId(courseId);


        //2.根据课程id删除章节
        eduChapterService.removeChapterByCourseId(courseId);


        //3.根据课程id删除描述
        eduCourseDescriptionService.removeById(courseId);

        //4.根据课程删除本身
        int result = baseMapper.deleteById(courseId);
        if(result == 0 ){
            throw  new GuliException(20001,"删除失败");
        }

    }
}
