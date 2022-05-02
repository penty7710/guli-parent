package com.pty.eduservice.client;

import com.pty.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**在调用端，创建interface，使用注解指定调用服务名称，定义调用的方法路径
 * 调用端表示需要调用服务的，也叫服务消费者，被调用的叫做服务提供者
 * @author : 彭天怡
 * @date : 2022/4/27 15:28
 */

//@FeignClient 表示调用的是服务名为 service-vod 的服务中的方法。fallback 表示出错后执行该类里面的方法
@Component
@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
public interface VodClient {

    //具体可以调用的方法，这里的方法路径是方法的完成路径，同时@PathVariable 需要指定参数名称
    //删除阿里云中的视频
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable("id") String id);


    //删除课程的所有视频
    @DeleteMapping("/eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);

}
