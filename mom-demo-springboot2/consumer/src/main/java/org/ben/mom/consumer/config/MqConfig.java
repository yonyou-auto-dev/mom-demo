package org.ben.mom.consumer.config;

import org.ben.mom.consumer.listener.EventListener;
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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.yonyou.cloud.mom.client.impl.MqSenderDefaultImpl;

@Configuration
public class MqConfig {
	@Bean
	public Queue pointsListenLoginQueue2() {
		return new Queue("consumer-b", true); // 队列持久
	}

	@Bean
	public FanoutExchange eventExchange() {
		return new FanoutExchange("ben_login");
	}

	
	@Bean
	public Binding PointsBindingLogin2() {
		return BindingBuilder.bind(pointsListenLoginQueue2()).to(eventExchange());
//				.with("queue-key");
	}

	
	
	@Bean
	public SimpleMessageListenerContainer messageContainer2(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueues(pointsListenLoginQueue2());
		container.setExposeListenerChannel(true);
		container.setMaxConcurrentConsumers(1);
		container.setConcurrentConsumers(1);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置确认模式手工确认
		container.setMessageListener(eventListener());
		container.setMaxConcurrentConsumers(10);//设置最大消费者数量 防止大批量涌入
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
