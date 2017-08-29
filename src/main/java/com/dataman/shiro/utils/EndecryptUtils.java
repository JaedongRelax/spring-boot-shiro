package com.dataman.shiro.utils;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.H64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.dataman.shiro.model.Accounts;

import java.security.Key;

public final class EndecryptUtils {
	/**
	 * base64进制加密
	 * 
	 * @param password
	 * @return
	 */
	public static String encrytBase64(String password) {
		// Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "不能为空");
		byte[] bytes = password.getBytes();
		return Base64.encodeToString(bytes);
	}

	/**
	 * base64进制解密
	 * 
	 * @param cipherText
	 * @return
	 */
	public static String decryptBase64(String cipherText) {
		// Preconditions.checkArgument(!Strings.isNullOrEmpty(cipherText), "消息摘要不能为空");
		return Base64.decodeToString(cipherText);
	}

	/**
	 * 16进制加密
	 * 
	 * @param password
	 * @return
	 */
	public static String encrytHex(String password) {
		// Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "不能为空");
		byte[] bytes = password.getBytes();
		return Hex.encodeToString(bytes);
	}

	/**
	 * 16进制解密
	 * 
	 * @param cipherText
	 * @return
	 */
	public static String decryptHex(String cipherText) {
		// Preconditions.checkArgument(!Strings.isNullOrEmpty(cipherText), "消息摘要不能为空");
		return new String(Hex.decode(cipherText));
	}

	public static String generateKey() {
		AesCipherService aesCipherService = new AesCipherService();
		Key key = aesCipherService.generateNewKey();
		return Base64.encodeToString(key.getEncoded());
	}
	/**
	 * 生成随机盐salt 对密码加密
	 * @param user
	 * @return
	 */
	public static Accounts md5_Password(Accounts user) {
		if (user.getSalt() == null) {
			SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
			String salt = secureRandomNumberGenerator.nextBytes().toHex();
			user.setSalt(salt);
		}
		String password_cipherText = new SimpleHash( "md5", user.getPassword(),ByteSource.Util.bytes(user.getUsername() + user.getSalt()) ,2).toString();
		user.setPassword(password_cipherText);
		return user;
	}

}
