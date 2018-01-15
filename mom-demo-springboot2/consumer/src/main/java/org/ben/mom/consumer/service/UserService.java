package org.ben.mom.consumer.service;

import org.ben.mom.consumer.entity.TmUser;
import org.ben.mom.consumer.mapper.TmUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.cloud.common.service.BaseService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;


@Service
@Transactional
public class UserService extends BaseService<Mapper<TmUser>, TmUser>{
	
	@Autowired
	TmUserMapper tmUserMapper;

	public void test() {
		TmUser u = new TmUser();
		u.setUserName("aaa");
		insert(u);
		
		TmUser a = new TmUser();
		a.setUserName("aaa");
		insert(a);
	}
	
	
	public void updateUser(String userName) {
		Example example = new Example(TmUser.class);
		example.createCriteria().andEqualTo("userName", userName);
		TmUser user = new TmUser();
		user.setUserName(userName+"a");
		tmUserMapper.updateByExampleSelective(user, example);
		int i = 1/0;
	}
}
