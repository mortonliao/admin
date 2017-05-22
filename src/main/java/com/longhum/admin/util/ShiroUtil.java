package com.longhum.admin.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.longhum.admin.model.SysUser;

public class ShiroUtil {
	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	
	public static void encryptUser(SysUser user){
		if(user == null){
			return ;
		}
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		
		String newPassword = new SimpleHash( "md5", user.getPassword(), 
				ByteSource.Util.bytes(user.getSalt()), 2).toHex();
		user.setPassword(newPassword);
			
	}
	
	public static void main(String[] args) {
		SysUser user = new SysUser();
		user.setUsername("admin");
		user.setPassword("1234qwer");
		encryptUser(user);
		System.out.println(user);
		//8fd187f3dbdc684f5f883a70e6252a2d
	}
}
