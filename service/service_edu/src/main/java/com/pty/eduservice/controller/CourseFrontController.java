package com.pty.eduservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pty.commonutils.JwtUtils;
import com.pty.commonutils.R;
import com.pty.eduservice.client.OrdersClient;
import com.pty.eduservice.entity.EduCourse;
import com.pty.eduservice.entity.chapter.ChapterVo;
import com.pty.eduservice.service.EduChapterService;
import com.pty.eduservice.service.EduCourseService;
import com.pty.eduservice.vo.CourseFrontVo;
import com.pty.eduservice.vo.CourseWebVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author : pety
 * @date : 2022/5/2 13:36
 */
@Slf4j
@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrdersClient client;

    //1 条件查询带分页查询课程
    //required = false 表示这个参数可以为null
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo){

        Page<EduCourse> coursePage = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(coursePage,courseFrontVo);
        return R.ok().data(map);
    }


    //2 课程详情的方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);

        //根据课程id和用户id查询该用户是否购买过该课程
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)){
            return R.error().message("用户未登录");
        }


        boolean buyCourse = client.isBuyCourse(courseId, memberId);

        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isBuy",buyCourse);
    }

    //根据课程id获取课程信息
    @PostMapping("getCourseInfoOrder/{id}")
    public R getCourseInfoOrder(@PathVariable String id){
        CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);
        return  R.ok().data("courseInfo",courseInfo);
    }

}
