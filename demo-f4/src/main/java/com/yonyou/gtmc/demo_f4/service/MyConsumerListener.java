package com.yonyou.gtmc.demo_f4.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.rabbitmq.client.Channel;
import com.yonyou.cloud.mom.client.consumer.AbstractConsumerListener;
import com.yonyou.cloud.mom.client.consumer.MomConsumer;

public abstract class MyConsumerListener<T extends Object> extends AbstractConsumerListener<T> {

	
	@Autowired
	MessageConverter messageConverter;
	/**
	 * 接收到消息 进行处理
	 */
	@Override
	@MomConsumer
	public void onMessage(Message message, Channel channel) {
		try {

			Object object = messageConverter.fromMessage(message);
			LOGGER.debug("msg content is  " + object);
			handleMessage((T) object);

		} catch (MessageConversionException e ) {

			LOGGER.error(e.toString(), e );
		}
	}
	
	@Override
	protected abstract void handleMessage(T data);


}
