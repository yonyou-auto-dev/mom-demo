package org.ben.mom.consumer.config;

import org.ben.mom.consumer.listener.EventListener;
import org.ben.mom.consumer.listener.LoginEventListener;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yonyou.cloud.mom.client.producer.MqSenderDefaultImpl;

import org.springframework.amqp.core.TopicExchange;

@Configuration
public class MqConfig {
	
	@Bean
	public Queue TopQueue() {
		return new Queue("consumer-queue", true); // 队列持久
	}
	
	@Bean
	public FanoutExchange fanOutExchange() {
		return new FanoutExchange("topExchangesss");
	}

	@Bean
	public Binding topBinding() {
		return BindingBuilder.bind(TopQueue()).to(fanOutExchange());
	}
	
	@Bean
	public SimpleMessageListenerContainer messageContainer2(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueues(TopQueue());
		container.setExposeListenerChannel(true);
		container.setConcurrentConsumers(10); // 默认多少个channel监听
		container.setPrefetchCount(10);// 每次取多少条
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置确认模式手工确认
		container.setMessageListener(eventListener());
		container.setMaxConcurrentConsumers(25);//设置最大消费者数量 防止大批量涌入
		return container;
	}
	
	@Bean
	public MessageConverter messageConverter() {
		JsonMessageConverter jsonMessageConverter = new JsonMessageConverter();
		return jsonMessageConverter;
	}
	
	@Bean
	public MqSenderDefaultImpl mqSenderDefaultImpl(RabbitOperations rabbitOperations) {
		MqSenderDefaultImpl mqSenderDefaultImpl = new MqSenderDefaultImpl();
		mqSenderDefaultImpl.setRabbitOperations(rabbitOperations);
		return mqSenderDefaultImpl;
	}

	
	@Bean
	public EventListener eventListener() {
		return new EventListener();
	}
	
}
