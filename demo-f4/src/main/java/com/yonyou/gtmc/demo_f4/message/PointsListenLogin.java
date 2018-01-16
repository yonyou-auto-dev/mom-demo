package com.yonyou.gtmc.demo_f4.message;

import org.springframework.stereotype.Service;

import com.yonyou.cloud.mom.client.consumer.AbstractConsumerListener;

@Service
public class PointsListenLogin extends AbstractConsumerListener<LoginMsg>{
	
	private static int counter = 0;

	@Override
	public void handleMessage(LoginMsg data) {
		LOGGER.error("handleMessage:"+data.getLoginName());
		LOGGER.error("count:{}",++counter);
	}

}
