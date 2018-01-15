package org.ben.mom.consumer.msg.callback.impl;

import java.util.Date;
import java.util.List;

import org.ben.mom.consumer.entity.ConsumerMsg;
import org.ben.mom.consumer.mapper.ConsumerMsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.cloud.mom.core.dto.ConsumerDto;
import com.yonyou.cloud.mom.core.store.callback.ConsumerStoreDbCallback;
import com.yonyou.cloud.mom.core.store.callback.exception.StoreDBCallbackException;

@Component
@Transactional
public class ConsumerCallbackImpl implements ConsumerStoreDbCallback{

	@Autowired
	ConsumerMsgMapper consumerMsgMapper;
	
	@Override
	public boolean exist(String msgKey) throws StoreDBCallbackException {
		if(consumerMsgMapper.selectByPrimaryKey(msgKey)!=null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isProcessing(String msgKey) throws StoreDBCallbackException {
		ConsumerMsg msg = new ConsumerMsg();
		msg.setMsgKey(msgKey);
		msg.setStatus(103);
		if(consumerMsgMapper.select(msg)!=null&&consumerMsgMapper.select(msg).size()==1) {
			return true;
		}
	
		return false;
	}

	@Override
	public void updateMsgProcessing(String msgKey, String data, String exchange, String routerKey,
			String consumerClassName, String bizClassName) throws StoreDBCallbackException {
		ConsumerMsg msg = new ConsumerMsg();
		msg.setMsgKey(msgKey);
		
		if(consumerMsgMapper.selectOne(msg)!=null) {
			msg.setStatus(103);
			consumerMsgMapper.updateByPrimaryKeySelective(msg);
		}else {
			msg.setBizClassName(bizClassName);
			msg.setConsumerClassName(consumerClassName);
			msg.setCreateTime(new Date());
			msg.setExchange(exchange);
			msg.setMsgContent(data);
			msg.setMsgKey(msgKey);
			msg.setRouterKey(routerKey);
			msg.setStatus(103);
			consumerMsgMapper.insert(msg);
		}
	}

	@Override
	public void updateMsgSuccess(String msgKey) throws StoreDBCallbackException {
		ConsumerMsg msg = new ConsumerMsg();
		msg.setMsgKey(msgKey);
		msg.setStatus(101);
		consumerMsgMapper.updateByPrimaryKeySelective(msg);
	}

	@Override
	public void updateMsgFaild(String msgKey) throws StoreDBCallbackException {
		ConsumerMsg msg = new ConsumerMsg();
		msg.setMsgKey(msgKey);
		msg.setStatus(102);
		consumerMsgMapper.updateByPrimaryKeySelective(msg);
		
	}

	@Override
	public List<ConsumerDto> selectReConsumerList(Integer status) {
		return null;
	}

}
