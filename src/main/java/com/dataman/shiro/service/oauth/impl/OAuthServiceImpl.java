package com.dataman.shiro.service.oauth.impl;

import org.springframework.stereotype.Service;

import com.dataman.shiro.service.oauth.OAuthService;
@Service
public class OAuthServiceImpl implements OAuthService {

	@Override
	public void addAuthCode(String authCode, String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAccessToken(String accessToken, String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkAuthCode(String authCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkAccessToken(String accessToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUsernameByAuthCode(String authCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsernameByAccessToken(String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getExpireIn() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean checkClientId(String clientId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkClientSecret(String clientSecret) {
		// TODO Auto-generated method stub
		return false;
	}

}
