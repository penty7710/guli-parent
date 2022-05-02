package com.pty.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pty.eduservice.entity.EduSubject;
import com.pty.eduservice.entity.excel.SubjectData;
import com.pty.eduservice.service.EduSubjectService;
import com.pty.eduservice.service.impl.EduSubjectServiceImpl;
import com.pty.servicebase.handler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 彭天怡 2022/4/22.
 */
@Component
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    @Autowired
    EduSubjectServiceImpl subjectService;

    //读取excel的内容
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null){
            throw  new GuliException(20001,"文件数据为空");
        }

        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        //判断一级分类是否重复
        EduSubject eduSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        //没有相同一级分类，进行添加
        if(eduSubject == null){
            eduSubject = new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(eduSubject);

        }

        //获取一级分类id值
        String pid = eduSubject.getId();

        //添加二级分类
        //判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            //二级分类名称
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }
    }

    /**
     * 判断一级分类有没有重复
     */
    private  EduSubject existOneSubject(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper();
        //构建查询条件
        wrapper.eq("title",name);
        wrapper.eq("parent_id",0);

        EduSubject oneSubject  = eduSubjectService.getOne(wrapper);
        return oneSubject;
    }

    /**
     * 判断二级分类有没有重复
     */
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
