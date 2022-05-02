package com.pty.eduservice.service;

import com.pty.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pty.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author pty
 * @since 2022-04-24
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);

}
