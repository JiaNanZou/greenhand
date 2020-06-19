package com.zjn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @BelongsProject: greenhand
 * @BelongsPackage: com.zjn
 * @Author: ZouJiaNan
 * @CreateTime: 2020-06-19 10:37
 * @Description:
 */
@SpringBootApplication
@EnableScheduling
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class,args);
    }

}
