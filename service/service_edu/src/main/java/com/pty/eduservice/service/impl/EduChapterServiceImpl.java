package com.pty.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pty.eduservice.entity.EduChapter;
import com.pty.eduservice.entity.EduVideo;
import com.pty.eduservice.entity.chapter.ChapterVo;
import com.pty.eduservice.entity.chapter.VideoVo;
import com.pty.eduservice.mapper.EduChapterMapper;
import com.pty.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pty.eduservice.service.EduVideoService;
import com.pty.servicebase.handler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author pty
 * @since 2022-04-24
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    //注入小节service
    private EduVideoService videoService;

    //根据课程id查询所有的章节和小节
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1 根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper);

        //2 根据课程id查询课程里面所有的小节
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);
        List<EduVideo> list = videoService.list(videoQueryWrapper);

        //创建list集合，用于最终封装数据
        List<ChapterVo> finalList = new ArrayList<>();

        //3 遍历查询章节list集合进行封装
        //遍历查询章节list集合
        for(int i=0;i<eduChapters.size();i++){
            //每个章节
            EduChapter eduChapter = eduChapters.get(i);
            //eduChapter对象值复制到ChapterVo里面
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            //把chapterVo放到最终list集合
            finalList.add(chapterVo);

            //创建集合，用于封装章节的小节
            List<VideoVo> videoList = new ArrayList<>();

            //4 遍历查询小节list集合，进行封装
            for (int m = 0; m < list.size(); m++) {
                //得到每个小节
                EduVideo eduVideo = list.get(m);
                //判断：小节里面chapterid和章节里面id是否一样
                if(eduVideo.getChapterId().equals(eduChapter.getId())) {
                    //进行封装
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    //放到小节封装集合
                    videoList.add(videoVo);
                }
            }
            //把封装之后小节list集合，放到章节对象里面
            chapterVo.setChildren(videoList);
        }
        return finalList;
    }


    /**
     * 删除章节
     */
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapterid章节id 查询小节表，如果查询数据，不进行删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = videoService.count(wrapper);

        if(count == 0){
            //删除章节
            int i = baseMapper.deleteById(chapterId);
            return i>0;
        }else{
            throw  new GuliException(20001,"不能删除");
        }
    }


    /**
     * 根据课程名称删除章节
     */
    @Override
    public void removeChapterByCourseId(String courseId) {
        baseMapper.delete(new QueryWrapper<EduChapter>().eq("course_id",courseId));
    }
}
