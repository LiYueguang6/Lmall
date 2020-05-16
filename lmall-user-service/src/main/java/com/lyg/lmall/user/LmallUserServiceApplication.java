package com.lyg.lmall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.lyg.lmall.user.mapper")
@SpringBootApplication
public class LmallUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmallUserServiceApplication.class, args);
    }

}
