package com.pty.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pty.commonutils.R;
import com.pty.eduservice.entity.EduCourse;
import com.pty.eduservice.entity.EduTeacher;
import com.pty.eduservice.service.EduCourseService;
import com.pty.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    //查询前8条热门课程，查询前4条名师
    @Cacheable(value = "banner",key = "'edu-teacherList'")
    @GetMapping("index")
    public R index() {
        //查询前8条热门课程（根据id查）
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");
        //拼接在sql语句的最后
        wrapper.last("limit 8");
        List<EduCourse> edulist = courseService.list(wrapper);


        //查询前4个名师(根据id查）
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);

        return R.ok().data("eduList",edulist).data("teacherList",teacherList);
    }

}
