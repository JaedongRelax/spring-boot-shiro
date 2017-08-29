package com.dataman.shiro.controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.http.HttpStatus;
@ControllerAdvice
public class DefaultExceptionHandler {
	/**
	 * 所有未通过验证的
	 * @param request
	 * @param e
	 * @return
	 */
    @ExceptionHandler( { UnauthorizedException.class })
    @ResponseStatus( HttpStatus.UNAUTHORIZED )
    public String processUnauthorizedException(NativeWebRequest request, UnauthorizedException e) {
    
    		return "403";
    	}
}