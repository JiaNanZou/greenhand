package com.zjn;

import com.zjn.dao.EmployeeRepository;
import com.zjn.pojo.po.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @BelongsProject: greenhand
 * @BelongsPackage: com.zjn
 * @Author: ZouJiaNan
 * @CreateTime: 2020-06-18 11:32
 * @Description:
 */
@SpringBootApplication
public class RestApplication {
    private static final Logger log = LoggerFactory.getLogger(RestApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class,args);
    }
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
            log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
        };
    }
}
