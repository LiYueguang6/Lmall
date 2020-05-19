package com.lyg.lmall.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.lyg.lmall.manage.mapper")
public class LmallManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmallManageServiceApplication.class, args);
    }

}
