package com.dataman.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {
	
	@RequestMapping(value = "/index")
	public ModelAndView home() {
		ModelAndView view = new ModelAndView("home");
		view.addObject("msg", "hello");
		return  view;
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView error() {
		ModelAndView view = new ModelAndView("403");
		return  view;
	}
	
}
