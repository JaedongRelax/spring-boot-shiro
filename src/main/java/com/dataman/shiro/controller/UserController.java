package com.dataman.shiro.controller;

import com.dataman.shiro.mapper.AccountMapper;
import com.dataman.shiro.model.Accounts;
import com.dataman.shiro.service.user.UserService;

import java.security.Security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@Autowired
	private UserService UserService;

	@Autowired
	public AccountMapper usermapper;

	@RequestMapping(value = "/reg")
	public String reg() {
		return "reg";
	}

	@RequestMapping(value = "/reg/do")
	public String doReg(String username, String password) {

		UserService.addUser(username, password);

		return "reg";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	/**
	 * 登陆不校验
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login/do", method = RequestMethod.POST)
	public ModelAndView dologin(String username, String password,Boolean rememberme) {
		System.out.println( username+username+rememberme);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		System.out.println("token" + token);
		
		if (rememberme!=null) {
			token.setRememberMe(rememberme);
		}
		
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
		} catch (IncorrectCredentialsException ice) {
			// 捕获密码错误异常
			ModelAndView view = new ModelAndView("login");
			view.addObject("message", "password error!");
			return view;
		} catch (UnknownAccountException uae) {
			// 捕获未知用户名异常
			ModelAndView view = new ModelAndView("login");
			view.addObject("message", "username error!");
			return view;
		} catch (ExcessiveAttemptsException eae) {
			// 捕获错误登录过多的异常
			ModelAndView view = new ModelAndView("login");
			view.addObject("message", "times error");
			return view;
		}
//		SecurityUtils.getSecurityManager().getClass()
		ModelAndView view = new ModelAndView("index");
		Accounts user = UserService.findUserByName(username);
		subject.getSession().setAttribute("user", user);
		
		view.addObject("message", "login success!");
		return view;
	}
	/**
	 * 注销
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.logout();
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "add";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update() {
		// 授权校验
		Subject subject = SecurityUtils.getSubject();
	
		if (subject.hasRole("admin")) {
			// 有权限
			return "update";
		} else {
			// 无权限
			return "403";
		}
	}

	@RequiresPermissions("delete-cicd-job")
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete() {

		return "delete";
	}
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public String select() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isPermitted("select")) {
			// 有权限
			return "select";
		} else {
			// 无权限
			return "403";
		}
	}
}
