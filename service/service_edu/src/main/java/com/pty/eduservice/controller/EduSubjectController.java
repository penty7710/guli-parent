package com.pty.eduservice.controller;


import com.pty.commonutils.R;
import com.pty.eduservice.entity.subject.OneSubject;
import com.pty.eduservice.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author pty
 * @since 2022-04-22
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    @ApiOperation("添加课程")
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        //上传过来excel文件
        subjectService.saveSubject(file);
        return R.ok();
    }


    @ApiOperation("课程分类列表")
    @GetMapping("getAllSubject")
    public R getAllSubject() {
        //list集合泛型是一级分类
        /*List<OneSubject> list = subjectService.getAllOneTwoSubject();*/
        List<OneSubject> list = subjectService.getAllsubject();
        return R.ok().data("list",list);
    }
}

