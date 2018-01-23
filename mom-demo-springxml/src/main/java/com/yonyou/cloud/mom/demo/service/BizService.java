package com.yonyou.cloud.mom.demo.service;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.cloud.mom.client.MqSender;
import com.yonyou.cloud.mom.demo.dao.TmBizMapper;
import com.yonyou.cloud.mom.demo.entity.TmBiz;
import com.yonyou.cloud.mom.demo.msg.entity.BizEntity;
import com.yonyou.cloud.mom.demo.msg.entity.LoginMsg;
 

@Service
@Transactional
public class BizService {
	
	
	@Autowired
	 MqSender mqSender;
	
	@Autowired
	TmBizMapper bizDao;
	
	
	public String saveLoginUser(String name) throws InterruptedException{
		
		TmBiz tmBiz = new TmBiz();
		tmBiz.setId(2);
		tmBiz.setName(name);
		bizDao.insert(tmBiz);
		
		LoginMsg msg = new LoginMsg();
		msg.setLoginName(name);
		msg.setLoginTime(new Date().getTime());
		
		mqSender.send("exchange-name", "queue-key", msg);
		
		if(name.equals("interrupt"))
			throw new RuntimeException("saveLoginUser err");
		
		return "1";
	}

}
