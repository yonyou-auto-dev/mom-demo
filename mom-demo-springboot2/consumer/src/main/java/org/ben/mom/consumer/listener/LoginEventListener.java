package org.ben.mom.consumer.listener;

import org.ben.mom.producer.msg.event.LoginEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.cloud.mom.client.consumer.AbstractConsumerListener;

@Service
//@Transactional
public class LoginEventListener extends AbstractConsumerListener<LoginEvent>{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 

	@Override
	public void handleMessage(LoginEvent data) {
		 logger.info("+++++++++++++++++++++++++++++这是第二个监听者+++++++++++++++++LoginEventListener");
	} 
	

}
