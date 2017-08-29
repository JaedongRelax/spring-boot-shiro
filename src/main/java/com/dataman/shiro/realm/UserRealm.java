package com.dataman.shiro.realm;

import java.util.HashSet;
import java.util.Set;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.dataman.shiro.model.Permissions;
import com.dataman.shiro.model.Roles;
import com.dataman.shiro.model.Accounts;
import com.dataman.shiro.service.user.impl.UserServiceImp;
public class UserRealm extends AuthorizingRealm {
	
	@Autowired
	private UserServiceImp userservice;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Accounts currentLoginUser = (Accounts) principals.getPrimaryPrincipal();
		System.out.println("当前登陆人-----------" + currentLoginUser.getUsername());
		Set<String> userRoles = new HashSet<>();
		Set<String> userPermissions = new HashSet<>();
		
		userRoles.add(currentLoginUser.getRoleList().get(0).getRole());
		
		for(Permissions permission : currentLoginUser.getRoleList().get(0).getPermissions()) {
			userPermissions.add(permission.getPermission());
			System.out.println("拥有权限>>>>"+permission.getPermission()+permission.getName());
			
		}
		
//		for(Roles role: currentLoginUser.getRoleList()) {
//			userRoles.add(role.getRole());
//			System.out.println("当前角色>>>>"+role.getRole()+role.getDescription());
//			for(Permissions permission : role.getPermissions()) {
//				userPermissions.add(permission.getPermission());
//				System.out.println("拥有权限>>>>"+permission.getPermission()+permission.getName());
//				
//			}
//		}
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 根据用户ID查询角色（role），放入到Authorization里。
		authorizationInfo.setRoles(userRoles);
		// 根据用户ID查询权限（permission），放入到Authorization里。
		authorizationInfo.setStringPermissions(userPermissions);

		System.out.println("###【获取角色成功】[SessionId] => {}" + SecurityUtils.getSubject().getSession().getId());

		return authorizationInfo;
	}

	@Override
	//校验
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取用户的输入的账号.
		String username = (String) token.getPrincipal();
		//通过username从数据库中查找 User对象，如果找到，没找到.
		Accounts userInfo = userservice.findUserByName(username);
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		if (userInfo != null) {
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo, // 用户名
					userInfo.getPassword(), // 密码
					ByteSource.Util.bytes(userInfo.getCredentialsSalt()), // salt=username+salt
					getName() // realm name
			);
			return authenticationInfo;
		} else {
			return null;
		}

	}
	
}
