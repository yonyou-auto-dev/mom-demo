package org.ben.mom.producer.service;

import org.ben.mom.producer.entity.ProducerMsg;
import org.springframework.stereotype.Service;

import com.yonyou.cloud.common.service.BaseService;

import tk.mybatis.mapper.common.Mapper;

@Service
public class MsgService extends BaseService<Mapper<ProducerMsg>, ProducerMsg>{

}
