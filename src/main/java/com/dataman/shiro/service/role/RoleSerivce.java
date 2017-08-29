package com.dataman.shiro.service.role;

import java.util.List;

import com.dataman.shiro.model.Roles;

public interface RoleSerivce {
	
	List<Roles> getAllRole(Integer tenantid);
}
