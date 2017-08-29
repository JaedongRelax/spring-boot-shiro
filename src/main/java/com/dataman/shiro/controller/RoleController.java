package com.dataman.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dataman.shiro.model.Accounts;
import com.dataman.shiro.service.role.RoleSerivce;

@Controller
public class RoleController {
	
	@Autowired
	private RoleSerivce roleservice;
	
	@RequestMapping(value = "/allRoles")
	@RequiresRoles("admin")
	public ModelAndView allRoles() {
		
		ModelAndView view = new ModelAndView("allroles");
		Accounts currUser =  (Accounts)SecurityUtils.getSubject().getPrincipal();
		
		
		view.addObject("rolelist", roleservice.getAllRole(currUser.getTenantid()));
		return view;
	}

}
