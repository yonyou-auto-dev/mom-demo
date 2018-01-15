package org.ben.mom.producer.msg.callback.impl;

import java.util.ArrayList;
import java.util.List;

import org.ben.mom.producer.entity.ProducerMsg;
import org.ben.mom.producer.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yonyou.cloud.mom.core.dto.ProducerDto;
import com.yonyou.cloud.mom.core.store.callback.ProducerStoreDBCallback;
import com.yonyou.cloud.mom.core.store.callback.exception.StoreDBCallbackException;

@Component
public class ProducerCallbackImpl implements ProducerStoreDBCallback{
	
	@Autowired
	MsgService msgService;

	@Override
	public void saveMsgData(String msgKey, String data, String exchange, String routerKey, String bizClassName)
			throws StoreDBCallbackException {
		
		ProducerMsg msg = new ProducerMsg();
		msg.setMsgKey(msgKey);
		msg.setMsgContent(data);
		msg.setExchange(exchange);
		msg.setRouterKey(routerKey);
		msg.setBizClassName(bizClassName);
		msg.setStatus(0);
		msgService.insert(msg);;
		
	}

	@Override
	public void update2success(String msgKey) throws StoreDBCallbackException {
		ProducerMsg msg = new ProducerMsg();
		msg.setMsgKey(msgKey);
		msg.setStatus(1);
		msgService.updateSelectiveById(msg);
	}

	@Override
	public void update2faild(String msgKey, String infoMsg, Long costTime, String exchange, String routerKey,
			String data, String bizClassName) throws StoreDBCallbackException {
		ProducerMsg msg = new ProducerMsg();
		msg.setMsgKey(msgKey);
		msg.setStatus(2);
		msgService.updateSelectiveById(msg);		
	}

	@Override
	public List<ProducerDto> selectResendList(Integer status) {
		ProducerMsg msg = new ProducerMsg();
		msg.setStatus(2);
		List<ProducerMsg> msgList = msgService.selectList(msg);
		List<ProducerDto> returnList = new ArrayList<ProducerDto>();
		for(int i = 0 ; i<msgList.size();i++) {
			ProducerDto returnDto = new ProducerDto();
			returnDto.setBizClassName(msgList.get(i).getBizClassName());
			returnDto.setExchange(msgList.get(i).getExchange());
			returnDto.setMsgContent(msgList.get(i).getMsgContent());
			returnDto.setMsgKey(msgList.get(i).getMsgKey());
			returnDto.setRouterKey(msgList.get(i).getRouterKey());
			returnDto.setStatus(status);
			returnList.add(returnDto);
		}
		
		return returnList;
	}

	

}
