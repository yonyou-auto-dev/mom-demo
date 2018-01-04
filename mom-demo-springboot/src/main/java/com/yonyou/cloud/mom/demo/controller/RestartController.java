package com.yonyou.cloud.mom.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.cloud.mom.client.MqSender;
import com.yonyou.cloud.mom.client.consumer.ReConsumerDefault;
@RestController
@RequestMapping("/restartMsg")
public class RestartController {
	@Autowired
	private ReConsumerDefault ReConsumerDefault;
	
	@Autowired
	private  MqSender mqsender;
	
	@RequestMapping("/reConsumer")
	public void taskReConsumer() {
		try {
			ReConsumerDefault.reConsumer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/reSend")
	public void mqSender() {
		System.out.println("开始重新发送了+++++++++++++++++++++");
		mqsender.resend();
	}

}
