package com.longhum.admin.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longhum.admin.dao.admin.TAuthorityMapper;
import com.longhum.admin.dao.admin.TRoleMapper;
import com.longhum.admin.dao.admin.TUserMapper;
import com.longhum.admin.model.TUser;
import com.longhum.admin.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private TUserMapper userDao;
	@Autowired
	private TRoleMapper roleDao;
	@Autowired
	private TAuthorityMapper authorityDao;

	@Override
	public TUser findById(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}
	@Override
	public Set<String> getRolesByUserId(Integer userId) {
		return roleDao.getRolesByUserId(userId);
	}
//	@Override
//	public Set<String> getGroupRolesByUserId(Integer userId) {
//		return userDao.getGroupRolesByUserId(userId);
//	}
//	@Override
//	public Set<String> getGroupPermissionsByUserId(Integer userId) {
//		return userDao.getGroupPermissionsByUserId(userId);
//	}
	@Override
	public Set<String> getPermissionsByUserId(Integer userId) {
		return authorityDao.getPermissionsByUserId(userId);
	}
	

}
