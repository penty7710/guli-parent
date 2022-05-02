package com.pty.eduservice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 课程条件查询vo
 * @author : pety
 * @date : 2022/5/2 13:34
 */
@Data
public class CourseFrontVo {

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "一级类别id")
    private String subjectParentId;

    @ApiModelProperty(value = "二级类别id")
    private String subjectId;

    @ApiModelProperty(value = "销量排序")
    private String buyCountSort;

    @ApiModelProperty(value = "最新时间排序")
    private String gmtCreateSort;

    @ApiModelProperty(value = "价格排序")
    private String priceSort;

}
