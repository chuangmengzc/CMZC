package com.chuangmeng.cmzc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chuangmeng.cmzc.*.dao")
public class CmzcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmzcApplication.class, args);
    }

}
