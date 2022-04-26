package com.pty.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pty.commonutils.R;
import com.pty.eduservice.entity.EduTeacher;
import com.pty.eduservice.service.EduTeacherService;
import com.pty.eduservice.vo.TeacherQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author pty
 * @since 2022-04-19
 */
@RestController
@RequestMapping("/eduservice/teacher")
@Api(tags = "讲师管理")
//解决跨域
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping("/findAll")
    public R list(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }


    @ApiOperation("根据ID删除讲师")
    @DeleteMapping("/{id}")
    public R removeByid(@ApiParam(name = "id",value = "讲师id") @PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        return R.ok();
    }

    @ApiOperation("分页讲师列表")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public R pageList(@PathVariable long current,@PathVariable long limit){
        //创建page对象，后面两个参数指定了limit的参数
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        /*调用方法实现分页
        调用方法的时候，底层封装，把分页所有数据封装到pageTeacher对象中
        底层的sql语句：
        SELECT id,name,intro,career,level,avatar,sort,is_deleted,gmt_create,gmt_modified
        FROM edu_teacher WHERE is_deleted=0 LIMIT 0,2
         */
        eduTeacherService.page(pageTeacher,null);

        //得到总的记录数
        long total = pageTeacher.getTotal();
        //查询到的数据的集合
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("条件讲师列表")
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public  R pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                   @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> teacherPage = new Page<>(current, limit);

        eduTeacherService.pageQuery(teacherPage,teacherQuery);

        long total = teacherPage.getTotal();

        List<EduTeacher> records = teacherPage.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }

    @PostMapping("/addTeacher")
    @ApiOperation("添加讲师")
    public  R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if(save){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @GetMapping("/getTeacher/{id}")
    @ApiOperation("根据id进行查询")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }

    @PostMapping("/updateTeacher")
    @ApiOperation("讲师修改")
    public R updateById(@RequestBody EduTeacher teacher){

        boolean flag = eduTeacherService.updateById(teacher);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

}

