package org.ben.mom.consumer.listener;
import org.ben.mom.consumer.mapper.TmUserMapper;
import org.ben.mom.consumer.service.UserService;
import org.ben.mom.producer.msg.event.LoginEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yonyou.cloud.mom.client.consumer.AbstractConsumerListener;
public class EventListener extends AbstractConsumerListener<LoginEvent>{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;
	
	@Autowired
	TmUserMapper tmUserMapper;

	public void handleMessage(LoginEvent data) {
		logger.info("处理登录事件");
//		Example example = new Example(TmUser.class);
//		example.createCriteria().andEqualTo("userName", data.getUserName());
//		TmUser user = new TmUser();
//		user.setUserName(data.getUserName()+"a");
//		tmUserMapper.updateByExampleSelective(user, example);
//		int i = 1/0;
//		userService.updateUser(data.getUserName());
		
		 logger.info("+++++++++++++++++++++++++++++这是第一个监听者+++++++++++++++++EventListener");
	}

	
	
	

}
