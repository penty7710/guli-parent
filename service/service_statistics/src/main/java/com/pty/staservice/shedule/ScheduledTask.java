package com.pty.staservice.shedule;

import cn.hutool.core.date.DateUtil;
import com.pty.staservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务类
 * @author : pety
 * @date : 2022/5/4 19:54
 */
@Component
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService staservice;

    //每天的一点自动执行这个方法,
    @Scheduled(cron = "0 0 1 * * ? ")
    public void task(){
        staservice.registerCount(DateUtil.yesterday().toString());
    }
}
