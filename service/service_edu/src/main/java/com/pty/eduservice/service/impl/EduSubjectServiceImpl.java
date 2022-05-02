package com.pty.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pty.eduservice.entity.EduSubject;
import com.pty.eduservice.entity.excel.SubjectData;
import com.pty.eduservice.entity.subject.OneSubject;
import com.pty.eduservice.entity.subject.TwoSubject;
import com.pty.eduservice.listener.SubjectExcelListener;
import com.pty.eduservice.mapper.EduSubjectMapper;
import com.pty.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author pty
 * @since 2022-04-22
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Autowired
    SubjectExcelListener listener;

    @Autowired
    EduSubjectMapper eduSubjectMapper;

    @Override
    public void saveSubject(MultipartFile file){
        try {
            //获取文件输入流
            InputStream inputStream = file.getInputStream();
            //调用方法执行读操作
            EasyExcel.read(inputStream, SubjectData.class,listener).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //1 查询所有一级分类  parentid = 0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        //2 查询所有二级分类  parentid != 0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //创建list集合，用于存储最终封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();


        //3 封装一级分类
        //查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值，
        for(int i=0;i<oneSubjectList.size();i++){
            //得到oneSubjectList中的每个eduSubject
            EduSubject eduSubject = oneSubjectList.get(i);

            //把eduSubject里面值获取出来，放到OneSubject对象里面
            //多个OneSubject放到finalSubjectList里面
            OneSubject oneSubject = new OneSubject();

           /* oneSubject.setId(eduSubject.getId());
            oneSubject.setTitle(eduSubject.getTitle());*/

            //功能和上面两段代码一样，简化了我们的开发
            //第一个参数是复制源，第二个是复制目的地
            //属性名称进行封装
            BeanUtils.copyProperties(eduSubject,oneSubject);

            finalSubjectList.add(oneSubject);

            //在一级分类循环遍历查询所有的二级分类
            //创建list集合封装每个一级分类的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            //遍历二级分类list集合
            for(int j =0;j<twoSubjectList.size();j++){
                EduSubject twoSubject = twoSubjectList.get(j);
                //判断二级分类parentid和一级分类id是否一样
                if(eduSubject.getId().equals(twoSubject.getParentId())){
                    TwoSubject tSubject = new TwoSubject();
                    //把tSubject值复制到TwoSubject里面，放到twoFinalSubjectList里面
                    BeanUtils.copyProperties(twoSubject,tSubject);
                    twoFinalSubjectList.add(tSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubjectList);

        }
        return  finalSubjectList;
    }

    @Override
    public List<OneSubject> getAllsubject() {
        return eduSubjectMapper.getAllsubject();
    }
}
