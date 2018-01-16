package com.yonyou.gtmc.demo_f4.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.cloud.mom.core.dto.ConsumerDto;
import com.yonyou.cloud.mom.core.store.StoreStatusEnum;
import com.yonyou.cloud.mom.core.store.callback.ConsumerStoreDbCallback;
import com.yonyou.cloud.mom.core.store.callback.exception.StoreDBCallbackException;

@Service
@Transactional
public class DemoMsgConsumerCallBack implements ConsumerStoreDbCallback{

	public boolean exist(String arg0) throws StoreDBCallbackException {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isProcessing(String arg0) throws StoreDBCallbackException {
		// TODO Auto-generated method stub
		return false;
	}

	public List<ConsumerDto> selectReConsumerList(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateMsgFaild(String arg0) throws StoreDBCallbackException {
		// TODO Auto-generated method stub
		
	}

	public void updateMsgProcessing(String arg0, String arg1, String arg2,
			String arg3, String arg4, String arg5)
			throws StoreDBCallbackException {
		// TODO Auto-generated method stub
		
	}

	public void updateMsgSuccess(String arg0) throws StoreDBCallbackException {
		// TODO Auto-generated method stub
		
	}


}
