package com.ele.item.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement/*事务回滚*/
@MapperScan("com.ele.item.database.mapper")
@ComponentScan(basePackages="com.ele.item")
public class EleBaseApplication {
	
	public static void main(String[] args) {
		System.out.println("EleBaseApplication starting ......");
		SpringApplication.run(EleBaseApplication.class, args);
		System.out.println("EleBaseApplication started ......");
	}
}
