package com.pty.servicebase.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常类
 * Created by 彭天怡 2022/4/19.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends  RuntimeException{
    //状态码
    private Integer code;

    //异常信息
    private String msg;
}
