package org.ben.mom.producer.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yonyou.cloud.mom.client.producer.MqSenderDefaultImpl;

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
			Jackson2JsonMessageConverter jsonMessageConverter = new Jackson2JsonMessageConverter();
			return jsonMessageConverter;
		}
		
		
		@Bean
		public FanoutExchange fanOutExchange() {
			return new FanoutExchange("topExchangesss");
		}
		
		@Bean
		public FanoutExchange fanOutExchange2() {
			return new FanoutExchange("mapExchange");
		}
}
