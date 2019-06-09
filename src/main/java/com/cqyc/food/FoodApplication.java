package com.cqyc.food;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@SpringBootApplication
@MapperScan("com.cqyc.food.mapper")
public class FoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodApplication.class);
    }
}
