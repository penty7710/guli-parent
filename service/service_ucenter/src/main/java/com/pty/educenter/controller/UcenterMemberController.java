package com.pty.educenter.controller;


import com.pty.commonutils.JwtUtils;
import com.pty.commonutils.R;
import com.pty.educenter.entity.UcenterMember;
import com.pty.educenter.service.UcenterMemberService;
import com.pty.educenter.vo.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author pty
 * @since 2022-04-29
 */
@CrossOrigin
@RestController
@RequestMapping("/educenter/member")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService ucenterMemberService;

    //登录
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member) {

        String token = ucenterMemberService.login(member);
        return R.ok().data("token",token);

    }

    //注册
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo) {
        ucenterMemberService.register(registerVo);
        return R.ok();
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = ucenterMemberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }

}

