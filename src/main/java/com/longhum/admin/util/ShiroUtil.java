package com.longhum.admin.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.longhum.admin.model.SysUser;

public class ShiroUtil {
	public static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	
	public static String encryptUser(String pwd,String salt){
		if(pwd == null || salt == null){
			return null;
		}
		//user.setSalt(randomNumberGenerator.nextBytes().toHex());
		
		String newPassword = new SimpleHash( "md5", pwd, 
				ByteSource.Util.bytes(salt), 2).toHex();
		return newPassword;
	}
	
	public static void main(String[] args) {
		String salt = "morton"+randomNumberGenerator.nextBytes().toHex();
		String pwd = encryptUser("morton",salt);
		System.out.println(salt+":"+pwd);
	}
}
