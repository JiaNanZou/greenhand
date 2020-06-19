package com.zjn;

import com.zjn.pojo.po.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @BelongsProject: greenhand
 * @BelongsPackage: com.zjn
 * @Author: ZouJiaNan
 * @CreateTime: 2020-06-19 10:50
 * @Description:
 */
@SpringBootApplication
public class JdbcApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(JdbcApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class,args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void run(String... args) throws Exception {
        log.info("创建数据库");
        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE customers(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        List<Object[]> splitUpNames = Arrays.asList("邹 佳南", "邹 鹿", "卡哇伊 伦纳德", "小 瓜皮").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        splitUpNames.forEach(name -> log.info(String.format("插入消费者记录%s %s",name[0],name[1])));

        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

        log.info("查询消费者记录");

        jdbcTemplate.query("SELECT * FROM customers WHERE first_name =?"
                ,new Object[]{"邹"}
                ,(rs,rowNum)->
                    new Customer(rs.getLong("id"),rs.getString("first_name"),rs.getString("last_name"))
                ).forEach(customer -> log.info(customer.toString()));

        Customer o = (Customer) jdbcTemplate.queryForObject("SELECT * FROM customers WHERE id =?"
                , new Object[]{1}
                , new BeanPropertyRowMapper(Customer.class)
        );
        log.info("查询单个" +o.toString());

        log.info("query records by custom BeanPropertyRowMapper");
        jdbcTemplate.query("SELECT * FROM customers",new BeanPropertyRowMapper<>(Customer.class)).forEach(customer -> log.info(customer.toString()));
        log.info("query records by custom RowMapper in java8");
        jdbcTemplate.query("SELECT * FROM customers",(rw,rowNum)->new Customer(rw.getLong("id"),rw.getString("first_name"),rw.getString("last_name")))
                .forEach(customer -> log.info(customer.toString()));

        log.info("queryForList");
        ArrayList<Customer> customers = new ArrayList<>();
        for (Map<String, Object> customerPropertyMap : jdbcTemplate.queryForList("SELECT * FROM customers")) {
            customers.add(new Customer((Integer) customerPropertyMap.get("id"), (String) customerPropertyMap.get("first_name"), (String) customerPropertyMap.get("last_name")));
        }
        customers.forEach(customer -> log.info(customer.toString()));

        log.info("query for single value");
        log.info( jdbcTemplate.queryForObject("SELECT last_name FROM customers WHERE id = ?",new Object[]{1L},String.class));
        log.info(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM customers",Integer.class)+"");

        log.info("way to deal millinos of data avoid OOM");
        jdbcTemplate.query("SELECT * FROM customers",rs->{
                Long id = rs.getLong("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                log.info(new Customer(id,first_name,last_name).toString());
        });



    }
}
