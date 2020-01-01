package com.ele.item.database;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement/*事务回滚*/
@MapperScan("com.ele.item.database.mapper")
@ComponentScan(basePackages="com.ele.item")
public class DasApplication {

	public static void main(String[] args) {
		System.out.println("DasApplication starting ......");
		SpringApplication.run(DasApplication.class, args);
		System.out.println("DasApplication started.");
	}
}
