package com.zjn;

import com.zjn.pojo.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @BelongsProject: greenhand
 * @BelongsPackage: com.zjn
 * @Author: yons
 * @CreateTime: 2020-06-19 10:10
 * @Description:
 */
@SpringBootApplication
public class AppApplication {
    private static final Logger log = LoggerFactory.getLogger(AppApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class,args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate){
        return args -> {
            Employee employee = restTemplate.getForObject("http://localhost:8080/employees/1", Employee.class);
            log.info(employee.toString());
        };
    }
}
