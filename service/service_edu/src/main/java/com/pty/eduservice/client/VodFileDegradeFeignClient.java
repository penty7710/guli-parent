package com.pty.eduservice.client;

import com.pty.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 创建远程调用接口的实现类，实现里面的具体的方法，当远程调用出错后，就会执行对应方法的具体实现
 * @author : pety
 * @date : 2022/4/27 19:13
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{

    /**
     *当远程调用使用接口的抽象方法时，如果发生了错误，则会执行该方法
     */
    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出错了");
    }
}
