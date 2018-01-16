package com.yonyou.gtmc.demo_f4.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.yonyou.cloud.mom.core.util.SpringUtil;
import com.yonyou.cloud.track.Track;

@Configuration 
//@ComponentScan(basePackages="com.yonyou.cloud.*")
public class MqConfig {
	
//	@Bean
//	public MqSenderDefaultImpl mqSenderDefaultImpl(CachingConnectionFactory f, MessageConverter converter) {
//		MqSenderDefaultImpl mqSenderDefaultImpl = new MqSenderDefaultImpl();
//		RabbitTemplate rabbitTemplate = new RabbitTemplate();
//		rabbitTemplate.setConnectionFactory(f);
//		rabbitTemplate.setMessageConverter(converter);
//		mqSenderDefaultImpl.setRabbitTemplate(rabbitTemplate);
////		mqSenderDefaultImpl.setRabbitOperations(rabbitOperations);
//		return mqSenderDefaultImpl;
//	}

	@Bean
	public SpringUtil springUtil() {
		return new SpringUtil();
	}
	@Value("${track.LoggingUrl:/opt/ops/track.log}")
	private String LoggingUrl;
	
	@Value("${track.isTacks:false}")
	private Boolean isTacks; 
	
	@Bean
	public Track initTrack() throws IOException {
		System.out.println("埋点初始化成功");
		if(isTacks) {
			return  new Track(new Track.ConcurrentLoggingConsumer(LoggingUrl));
		}else {
			return null; 
		}
		
	}
//
//	@Bean
//	public ProducerMsgStore getDbStoreProducerMsg() {
//		return new DbStoreProducerMsg();
//	}

//	@Bean
//	public Queue pointsListenLoginQueue() {
//		return new Queue("points-login", true); // 队列持久
//	}
//	
//	@Bean
//	public DirectExchange eventExchange() {  
//        return new DirectExchange("event-exchange");
//    }  
//	
//	@Bean
//	public Binding PointsBindingLogin(){
//		return BindingBuilder.bind(pointsListenLoginQueue()).to(eventExchange()).with("login");
//	}
	

//	@Bean
//	public SimpleMessageListenerContainer messageContainer1(CachingConnectionFactory f) {
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(f);
//		container.setQueues(pointsListenLoginQueue());
//		container.setExposeListenerChannel(true);
//		container.setMaxConcurrentConsumers(5);
//		container.setConcurrentConsumers(1);
//		container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置确认模式手工确认
//		container.setMessageListener(pointsListenLogin());
//		return container;
//	}
	
//	@Bean
//	public CachingConnectionFactory cachingConnectionFactory(){
//		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host, port);
//		cachingConnectionFactory.setUsername("test");
//		cachingConnectionFactory.setPassword("test");
//		return cachingConnectionFactory;
//	}
	
//	@Bean
//	public MessageConverter messageConverter(){
//		JsonMessageConverter jsonMessageConverter = new JsonMessageConverter();
//		return jsonMessageConverter;
//	}
	
	
//	@Bean
//	public PointsListenLogin pointsListenLogin(){
//		return new PointsListenLogin();
//	}
//	
}
