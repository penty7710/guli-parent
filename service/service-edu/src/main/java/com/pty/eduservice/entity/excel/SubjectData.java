package com.pty.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 *excel文件对应的实体类
 * Created by 彭天怡 2022/4/22.
 */
@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private  String oneSubjectName;
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
