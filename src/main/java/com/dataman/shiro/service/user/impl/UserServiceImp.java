package com.dataman.shiro.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataman.shiro.mapper.AccountMapper;
import com.dataman.shiro.model.Accounts;
import com.dataman.shiro.service.user.UserService;
import com.dataman.shiro.utils.EndecryptUtils;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	public AccountMapper usermapper;
	
	@Override
	public Accounts findUserByName(String username) {
		return usermapper.findUserByName(username);
	}

	@Override
	public void addUser(String username, String password) {
		Accounts user = new Accounts();
		user.setUsername(username);
		user.setPassword(password);
		
		user.setState(new Byte("1"));
		
		EndecryptUtils.md5_Password(user);
		usermapper.addUser(user);
	}
}
