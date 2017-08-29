package com.dataman.shiro.service.user;

import com.dataman.shiro.model.Accounts;

public interface UserService {
	
	 Accounts findUserByName(String username);
	 
	 void addUser(String username,String password);
}
