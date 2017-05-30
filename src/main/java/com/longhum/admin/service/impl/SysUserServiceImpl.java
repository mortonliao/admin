package com.longhum.admin.service.impl;

import java.util.List;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longhum.admin.dao.SysUserMapper;
import com.longhum.admin.model.SysUser;
import com.longhum.admin.service.SysUserService;
import com.longhum.admin.util.ShiroUtil;

@Service
public class SysUserServiceImpl implements SysUserService{
	
	@Autowired
	private SysUserMapper userDao;

	@Override
	public SysUser findById(Long id) {
		return userDao.findById(id);
	}
//	@Override
//	public Set<String> getRolesByUserId(Long userId) {
//		return roleDao.getRolesByUserId(userId);
//	}
//	@Override
//	public Set<String> getGroupRolesByUserId(Integer userId) {
//		return userDao.getGroupRolesByUserId(userId);
//	}
//	@Override
//	public Set<String> getGroupPermissionsByUserId(Integer userId) {
//		return userDao.getGroupPermissionsByUserId(userId);
//	}
//	@Override
//	public Set<String> getResourceByUserId(Long userId) {
//		return resourceDao.getResourceByUserId(userId);
//	}
	@Override
	public SysUser findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	@Override
	public List<SysUser> findAll() {
		return userDao.findAll();
	}
	@Override
	public void saveUser(SysUser user) {
		if(user.getId() != null){
			userDao.update(user);
		}else{
			user.setSalt(ShiroUtil.randomNumberGenerator.nextBytes().toHex());
			String pwd = ShiroUtil.encryptUser(user.getPassword(),user.generateSalt());
			user.setPassword(pwd);
			userDao.save(user);
		}
	}
	@Override
	public void deleteUser(Long userId) {
		userDao.delete(userId);
	}
	@Override
	public void resetPwd(List<Long> ids) {
		if(ids != null){
			for (Long id : ids) {
				SysUser user = userDao.findById(id);
				String pwd = ShiroUtil.encryptUser(user.getUsername()+"1234",user.generateSalt());
				user.setPassword(pwd);
				userDao.update(user);
			}
		}
	}
	

}
