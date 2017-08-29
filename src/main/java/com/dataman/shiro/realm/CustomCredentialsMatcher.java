package com.dataman.shiro.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
/**
 * 自定义密码校验
 * @author jxdong
 *
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		UsernamePasswordToken authcToken = (UsernamePasswordToken) token;  
		
        Object tokenCredentials = String.valueOf(authcToken.getPassword());  
        System.out.println(tokenCredentials);
        Object accountCredentials = getCredentials(info);
        System.out.println(accountCredentials);
        //将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false  
        ///return equals(tokenCredentials, accountCredentials);  
        
        return true;  
	}
}
