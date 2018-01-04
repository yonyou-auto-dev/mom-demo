package com.yonyou.cloud.mom.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring4all.swagger.EnableSwagger2Doc;

@SpringBootApplication
@EnableSwagger2Doc
public class MomDemoSpringbootApplication {
	public static void main(String[] args) {
		SpringApplication.run(MomDemoSpringbootApplication.class, args);
	}
	
}
