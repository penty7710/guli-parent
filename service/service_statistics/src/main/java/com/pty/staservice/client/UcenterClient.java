package com.pty.staservice.client;

import com.pty.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用ucenter模块
 * @author : pety
 * @date : 2022/5/4 17:12
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    //查询某一天注册人数
    @GetMapping("/educenter/member/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day);
}
