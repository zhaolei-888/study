package com.example.businessdemo;

import com.example.businessdemo.controller.BatchController;
import com.example.businessdemo.utils.SpringBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@MapperScan("com.example.businessdemo.mapper")
@ComponentScans(value = {@ComponentScan("com.example.esdemo"),
        @ComponentScan("com.example.businessdemo.controller")})
public class BusinessDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessDemoApplication.class, args);
    }

//    @Bean
//    public void loadBean(){
//        BatchController controller = SpringBeanUtil.getBean(BatchController.class);
//        controller.sendMessage();
//    }
}
