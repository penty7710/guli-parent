package com.pty.servicebase.handler;

import com.pty.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 * Created by 彭天怡 2022/4/19.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //指定这个方法用来接收什么异常
    @ExceptionHandler(Exception.class)
    //用来返回数据
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }


    //处理特定异常，会优先检查是否有特定的异常处理器，如果有回去执行特定处理器，没有才会去执行全局异常处理器
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理");
    }

    //处理自定义异常，自定义异常需要手动使用throw抛出异常
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e){
        e.printStackTrace();
        //设置返回值
        return  R.error().code(e.getCode()).message(e.getMsg());
    }

}
