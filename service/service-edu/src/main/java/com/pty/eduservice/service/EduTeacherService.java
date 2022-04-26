package com.pty.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pty.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pty.eduservice.vo.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author pty
 * @since 2022-04-19
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);
}
