package com.ele.item.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EleDasApplication {

	public static void main(String[] args) {
		System.out.println("DasApplication starting ......");
		SpringApplication.run(EleDasApplication.class, args);
		System.out.println("DasApplication started.");
	}
}
