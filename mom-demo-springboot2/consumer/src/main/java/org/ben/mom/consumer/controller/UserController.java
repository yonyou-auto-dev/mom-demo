package org.ben.mom.consumer.controller;

import org.ben.mom.consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping(value="/test")
	public String testTransaction() {
		userService.test();
		return "a";
	}
}
