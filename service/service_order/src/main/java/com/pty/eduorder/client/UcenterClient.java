package com.pty.eduorder.client;

import com.pty.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 远程调用Ucenter模块
 * @author : pety
 * @date : 2022/5/3 10:02
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    public R getUserInfoOrder(@PathVariable("id") String id);
}
