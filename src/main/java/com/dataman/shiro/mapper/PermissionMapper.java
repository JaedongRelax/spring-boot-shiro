package com.dataman.shiro.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.dataman.shiro.model.Permissions;

public interface PermissionMapper {
	/**
	 * 根据角色查询权限
	 * @param roleid
	 * @return
	 */
	@Select("SELECT * FROM sry_auth_permissions a,sry_auth_role_permission b  WHERE a.id = b.permission_id and b.role_id = #{roleid}")
	@Results({
		@Result(id = true ,property = "id",  column = "id"),
		@Result(property = "name",  column = "name"),
		@Result(property = "resourceType",  column = "resource_type"),
		@Result(property = "url",  column = "url"),
		@Result(property = "permission",  column = "permission")
	})
	List<Permissions> findPermissionByRole(String roleid);
	
	@Select("SELECT * FROM sys_permission")
	List<Permissions> allPermission();
}
