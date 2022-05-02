package com.pty.eduservice.service;

import com.pty.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pty.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author pty
 * @since 2022-04-22
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file);

    List<OneSubject> getAllOneTwoSubject();

    List<OneSubject> getAllsubject();

}
