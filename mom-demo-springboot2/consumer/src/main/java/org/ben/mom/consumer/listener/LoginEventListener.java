//package org.ben.mom.consumer.listener;
//
//import org.ben.mom.consumer.entity.TmUser;
//import org.ben.mom.consumer.mapper.TmUserMapper;
//import org.ben.mom.consumer.service.UserService;
//import org.ben.mom.producer.msg.event.LoginEvent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
//import org.springframework.amqp.support.converter.MessageConversionException;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.rabbitmq.client.Channel;
//import com.yonyou.cloud.mom.client.consumer.AbstractConsumerJustListen;
//import com.yonyou.cloud.mom.client.consumer.AbstractConsumerListener;
//
//import tk.mybatis.mapper.entity.Example;
//
//@Service
//@Transactional
//public class LoginEventListener implements ChannelAwareMessageListener{
//	
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//	
//	@Autowired
//	UserService userService;
//	
//	@Autowired
//	TmUserMapper tmUserMapper;
//
//	protected void handleMessage(LoginEvent data) {
//		logger.info("处理登录事件");
//		Example example = new Example(TmUser.class);
//		example.createCriteria().andEqualTo("userName", data.getUserName());
//		TmUser user = new TmUser();
//		user.setUserName(data.getUserName()+"a");
//		tmUserMapper.updateByExampleSelective(user, example);
//		int i = 1/0;
//	}
//	
//	
//	protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractConsumerJustListen.class);
//
//	@Autowired
//	MessageConverter messageConverter;
//
//	/**
//	 * 接收到消息 进行处理
//	 */
//	@Override
//	public void onMessage(Message message, Channel channel) {
//		try {
//
//			Object object = messageConverter.fromMessage(message);
//			LOGGER.debug("msg content is  " + object);
//			handleMessage((LoginEvent) object);
//
//		} catch (MessageConversionException e ) {
//
//			LOGGER.error(e.toString(), e );
//		}
//	}
//	
//	
//	
//	
//
//}
