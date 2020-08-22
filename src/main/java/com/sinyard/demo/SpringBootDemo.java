package com.sinyard.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author sinyard
 * @date 2020-08-06 16:58
 * @desc
 */
@MapperScan("com.sinyard.demo.dao") //扫描mapper
@SpringBootApplication
public class SpringBootDemo {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(SpringBootDemo.class,args);
    }
}
