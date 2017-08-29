package com.dataman.shiro.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.dataman.shiro.model.Roles;

import org.apache.ibatis.annotations.Many;

public interface RoleMapper {
	/**
	 * 根据用户查询角色
	 * @param userid
	 * @return
	 */
	@Select("SELECT * FROM sry_auth_roles a,sry_auth_account_role b,sry_auth_groups c WHERE a.id = b.role_id and b.group_id= c.id and b.uid = #{userid}")
	@Results({
		@Result(id = true, property = "id",  column = "id"),
		@Result(property = "role",  column = "role"),
		@Result(property = "description",  column = "description"),
		@Result(property = "groupid",  column = "group_id"),
		@Result(property = "groupname",  column = "name"),
		@Result(property="permissions", column="id",
			many =@Many(select = "com.dataman.shiro.mapper.PermissionMapper.findPermissionByRole")) 
	})
	List<Roles> findRoleByUser(String userid);
	
	@Select("SELECT id,role,description,available FROM sry_auth_roles where tenant_id = #{tenantid}")
	@Results({
		@Result(id = true, property = "id",  column = "id"),
		@Result(property = "role",  column = "role"),
		@Result(property = "description",  column = "description"),
		@Result(property = "available",  column = "available")
	})
	List<Roles> selectAllRole(Integer tenantid);
}
