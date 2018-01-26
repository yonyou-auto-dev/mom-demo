package org.ben.mom.producer.config;

import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yonyou.cloud.mom.client.producer.MqSenderDefaultImpl;

import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class MqConfig {
	
		@Bean
		public MqSenderDefaultImpl mqSenderDefaultImpl(RabbitOperations rabbitOperations) {
			MqSenderDefaultImpl mqSenderDefaultImpl = new MqSenderDefaultImpl();
			mqSenderDefaultImpl.setRabbitOperations(rabbitOperations);
			return mqSenderDefaultImpl;
		}
		
		@Bean
		public MessageConverter messageConverter() {
			JsonMessageConverter jsonMessageConverter = new JsonMessageConverter();
			return jsonMessageConverter;
		}
}
