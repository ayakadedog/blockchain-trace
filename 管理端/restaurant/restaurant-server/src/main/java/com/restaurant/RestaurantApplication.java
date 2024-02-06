package com.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Slf4j
//@EnableCaching //缓存注解功能
@EnableScheduling //开启任务调度
@SpringBootApplication
@EnableTransactionManagement //开启注解方式的事务管理
public class RestaurantApplication {

    public static void main(String[] args) {

        SpringApplication.run(RestaurantApplication.class, args);
        log.info("server started");
//        A .addProductRecord();
//        A.addTask();
//        A.addTransport();
    }
}
