package com.pty.eduservice.mapper;

import com.pty.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pty.eduservice.entity.subject.OneSubject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author pty
 * @since 2022-04-22
 */
@Repository
public interface EduSubjectMapper extends BaseMapper<EduSubject> {
        List<OneSubject>  getAllsubject();
}
