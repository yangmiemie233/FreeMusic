package com.kerwin.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


//当前类是springboot的启动类
@SpringBootApplication
@ComponentScan("com")
public class ApplicationRunning {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunning.class);
    }
}