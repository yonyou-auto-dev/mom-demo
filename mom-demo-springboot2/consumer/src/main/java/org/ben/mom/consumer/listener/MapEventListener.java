package org.ben.mom.consumer.listener;
import java.util.HashMap;

import org.ben.mom.consumer.mapper.TmUserMapper;
import org.ben.mom.consumer.service.UserService;
import org.ben.mom.producer.msg.event.LoginEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaoleilu.hutool.util.RandomUtil;
import com.yonyou.cloud.mom.client.consumer.AbstractConsumerListener;

@Component
public class MapEventListener extends AbstractConsumerListener<HashMap<String, Object>>{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;
	
	@Autowired
	TmUserMapper tmUserMapper;

	public void handleMessage(HashMap<String,Object> data) throws Exception {
		logger.info("处理登录事件,类型是MAP");
//		Example example = new Example(TmUser.class);
//		example.createCriteria().andEqualTo("userName", data.getUserName());
//		TmUser user = new TmUser();
//		user.setUserName(data.getUserName()+"a");
//		tmUserMapper.updateByExampleSelective(user, example);
//		int i = 1/0;
//		userService.updateUser(data.getUserName());
		
		logger.info("msg ===  "+data);
		
		int i  = RandomUtil.randomInt(1, 10);
		if(i>5) {
			throw new Exception("int 》5 ");
		}
		
		userService.updateUser(data.get("userName").toString());
		
		 logger.info("+++++++++++++++++++++++++++++这是第一个监听者+++++++++++++++++EventListener");
	}

	
	
	

}
