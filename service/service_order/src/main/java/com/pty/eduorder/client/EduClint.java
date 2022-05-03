package com.pty.eduorder.client;

import com.pty.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 远程调用eduservice模块
 * @author : pety
 * @date : 2022/5/3 10:02
 */
@Component
@FeignClient("service-edu")
public interface EduClint {

    @PostMapping("/eduservice/coursefront/getCourseInfoOrder/{id}")
    public R getCourseInfoOrder(@PathVariable("id") String id);
}
