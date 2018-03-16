package com.test.service.impl;


import com.test.dao.UserMapper;
import com.test.model.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Transactional
	@Override
	public int addUser(User user)  throws Exception{
		userMapper.insert(user);
		return 1;
	}
	
	@Override
	public User getUser(String account) throws Exception {
		User user = userMapper.selectByPrimaryKey(account);
		return user;
	}
	@Transactional
	@Override
	public int updateByPrimaryKey(String uid, String password, String newpassword) throws Exception{
		int ret = 0;
		User user = userMapper.selectByPrimaryKey(uid);

		if(user != null && user.getuPwd().equals(password))
		{
			user.setuPwd(newpassword);
			userMapper.updateByPrimaryKey(user);

			ret = 1;
		}
		else
		{
			ret = 0;
		}
		return ret;
	}
}
