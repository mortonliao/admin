package com.longhum.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longhum.admin.dao.admin.TUserMapper;
import com.longhum.admin.model.TUser;
import com.longhum.admin.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private TUserMapper userDao;
	@Override
	public TUser findById(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}
	

}
