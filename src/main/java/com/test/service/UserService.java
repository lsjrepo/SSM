package com.test.service;


import com.test.model.User;

public interface UserService {
	public int addUser(User user) throws Exception;
	public User getUser(String account) throws Exception;
	public  int updateByPrimaryKey(String uid, String password, String newpassword) throws Exception;
}
