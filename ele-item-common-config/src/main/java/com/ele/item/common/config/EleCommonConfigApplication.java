package com.ele.item.common.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ele.item")
public class EleCommonConfigApplication {

	public static void main(String[] args) {
		System.out.println("EleCommonConfigApplication starting ......");
		SpringApplication.run(EleCommonConfigApplication.class, args);
		System.out.println("EleCommonConfigApplication started ......");
	}

}
