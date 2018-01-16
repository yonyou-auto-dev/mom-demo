package com.yonyou.gtmc.demo_f4.message;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.cloud.mom.core.dto.ProducerDto;
import com.yonyou.cloud.mom.core.store.callback.ProducerStoreDBCallback;
import com.yonyou.cloud.mom.core.store.callback.exception.StoreDBCallbackException;
import com.yonyou.gtmc.demo_f4.model.Emp;

@Service
@Transactional
public class DemoMsgProducerCallBack implements ProducerStoreDBCallback {
	Logger log=LoggerFactory.getLogger(DemoMsgProducerCallBack.class);

	public void saveMsgData(String arg0, String arg1, String arg2, String arg3,
			String arg4) throws StoreDBCallbackException {
		// TODO Auto-generated method stub
//		log.error("saveMsgData");
//		Emp emp = new Emp();
//    	emp.set("ename", "saveMsgData" + new Date());
//		emp.insert();
	}

	public List<ProducerDto> selectResendList(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update2faild(String arg0, String arg1, Long arg2, String arg3,
			String arg4, String arg5, String arg6)
			throws StoreDBCallbackException {
		// TODO Auto-generated method stub
		
	}

	public void update2success(String arg0) throws StoreDBCallbackException {
		// TODO Auto-generated method stub
		
	}

}
