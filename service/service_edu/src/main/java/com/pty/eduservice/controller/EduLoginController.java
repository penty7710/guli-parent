package com.pty.eduservice.controller;

import com.pty.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 彭天怡 2022/4/20.
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
@Api(tags = "登录管理")
public class EduLoginController {

    @ApiOperation("登录")
    @PostMapping("/login")
    public R login() {
        return R.ok().data("token","admin");
    }

    @ApiOperation("获取信息")
    @GetMapping("/info")
    public R info() {
        return R.ok().data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
