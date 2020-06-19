package com.zjn.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @BelongsProject: greenhand
 * @BelongsPackage: com.zjn.component
 * @Author: ZouJiaNan
 * @CreateTime: 2020-06-19 10:39
 * @Description:
 */
@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelay = 5000)
    public void reportCurrentTime(){
        log.info("现在是的时间是{}",dateFormat.format(new Date()));
    }
}
