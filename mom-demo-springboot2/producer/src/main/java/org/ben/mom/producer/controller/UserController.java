package org.ben.mom.producer.controller;

import org.ben.mom.producer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.cloud.common.annotation.YcApi;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping("/login")
	@YcApi
	public String userLogin(String name) {
		userService.userLogin(name);
		return "hello "+name;
	}
}
