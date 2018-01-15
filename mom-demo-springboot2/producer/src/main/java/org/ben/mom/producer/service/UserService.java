package org.ben.mom.producer.service;

import org.ben.mom.producer.entity.TmUser;
import org.ben.mom.producer.msg.event.LoginEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.cloud.common.service.BaseService;
import com.yonyou.cloud.mom.client.MqSender;

import tk.mybatis.mapper.common.Mapper;

@Service
@Transactional
public class UserService extends BaseService<Mapper<TmUser>, TmUser>{
	
	@Autowired
	MqSender mqSender;

	public boolean userLogin(String userName) {
		TmUser user = new TmUser();
		user.setUserName(userName);
		insert(user);
		
		LoginEvent event = new LoginEvent();
		event.setUserName(userName);
		
		mqSender.send("ben_login", "login", event);
		
		
		
		return true;
	}
}
