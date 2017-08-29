package com.dataman.shiro.service.role.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataman.shiro.mapper.RoleMapper;
import com.dataman.shiro.model.Roles;
import com.dataman.shiro.service.role.RoleSerivce;
@Service
public class RoleSerivceImpl implements RoleSerivce {
	@Autowired
	private RoleMapper rolemapper;
	

	@Override
	public List<Roles> getAllRole(Integer tenantid) {
		// TODO Auto-generated method stub
		return rolemapper.selectAllRole(tenantid);
	}

}
