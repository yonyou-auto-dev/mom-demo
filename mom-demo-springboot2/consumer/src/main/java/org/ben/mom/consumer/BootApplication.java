package org.ben.mom.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring4all.swagger.EnableSwagger2Doc;

/**
 * 入口函数
 * 
 * @author BENJAMIN
 *
 */
@SpringBootApplication
@EnableSwagger2Doc
//@EnableDiscoveryClient
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
}
