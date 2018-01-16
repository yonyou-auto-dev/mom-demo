package com.yonyou.gtmc.demo_f4.service;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonyou.cloud.mom.client.MqSender;
import com.yonyou.gtmc.demo_f4.message.LoginMsg;
import com.yonyou.gtmc.demo_f4.model.Emp;


@Service
//@TxnConn
public class BizService {
	
	private static final Logger logger = LoggerFactory.getLogger(BizService.class);

	@Autowired
	private MqSender mqSender;

	public class testRunnable implements Runnable {
	 
	    public void run() {
	    	Emp emp = new Emp();
	    	emp.set("ename", "Runnable" + System.currentTimeMillis() + "THREAD:"+Thread.currentThread().getName());
			emp.insert();
	    }
	}
	
	private ExecutorService executor = Executors.newFixedThreadPool(10);
	
	public String saveLoginUser(String name) throws InterruptedException{
		LoginMsg msg = new LoginMsg();
		msg.setLoginName(name);
		msg.setLoginTime(new Date().getTime());
		mqSender.send("mqMsgF4Exchange", "login", msg);
		Emp emp = new Emp();
		
		LazyList<Model> all = emp.findAll();
		logger.error("all size:" + all.size());
		emp.set("ename", name + "THREAD:"+Thread.currentThread().getName());
		emp.insert();
		
		
		if(name.equals("interruption")) throw new RuntimeException();
		return "success";
	}

	public String saveLoginUser2(String name) {
		LoginMsg msg = new LoginMsg();
		msg.setLoginName(name);
		msg.setLoginTime(new Date().getTime());
		mqSender.justSend("amqpExchange", "login", msg);
		Emp emp = new Emp();
		
		LazyList<Model> all = emp.findAll();
		logger.error("all size:" + all.size());
		emp.set("ename", name + "THREAD:"+Thread.currentThread().getName());
		emp.insert();
		
		if(name.equals("interruption")) throw new RuntimeException();
		return "success";
	}
}
