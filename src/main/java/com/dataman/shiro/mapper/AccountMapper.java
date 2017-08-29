package com.dataman.shiro.mapper;


import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.dataman.shiro.model.Accounts;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;

public interface AccountMapper {
	
	/**根据用户名查询用户 及其 角色 权限
	 * 
	 * @param name
	 * @return
	 */
	@Select("SELECT * FROM sry_auth_accounts a, sry_auth_tenants c WHERE a.tenant_id = c.id and a.username = #{name}")
	@Results({
		@Result(property = "id",  column = "id"),
		@Result(property = "username",  column = "username"),
		@Result(property = "name",  column = "name"),
		@Result(property = "password",  column = "password"),
		@Result(property = "salt",  column = "salt"),
		@Result(property = "state",  column = "state"),
		@Result(property = "tenantid",  column = "tenant_id"),
		@Result(property = "tenantname",  column = "tenant_name"),
		@Result(property="roleList", column="id",
		many =@Many(select = "com.dataman.shiro.mapper.RoleMapper.findRoleByUser")) 
	})
	Accounts findUserByName(String name);

	/**
	 * 添加用户
	 * @param user
	 */
	@Insert("INSERT INTO user_info(id,username,name,password,salt,state,tenant_id) VALUES(#{id},#{username},#{name},#{password},#{salt},#{state},#{tenantid})")
	void addUser(Accounts user);
}
