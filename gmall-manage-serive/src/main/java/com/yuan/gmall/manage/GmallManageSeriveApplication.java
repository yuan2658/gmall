package com.yuan.gmall.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.yuan.gmall.manage.mapper")
public class GmallManageSeriveApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallManageSeriveApplication.class, args);
    }

}
