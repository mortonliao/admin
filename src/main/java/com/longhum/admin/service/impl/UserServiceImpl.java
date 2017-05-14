package com.longhum.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longhum.admin.dao.admin.UserMapper;
import com.longhum.admin.model.User;
import com.longhum.admin.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userDao;
	@Override
	public User findById(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}
	

}
