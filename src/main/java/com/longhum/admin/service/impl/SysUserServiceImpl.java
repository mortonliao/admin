package com.longhum.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longhum.admin.dao.SysUserMapper;
import com.longhum.admin.model.SysUser;
import com.longhum.admin.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService{
	
	@Autowired
	private SysUserMapper userDao;

	@Override
	public SysUser findById(Long id) {
		return userDao.selectByPrimaryKey(id);
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
	

}
