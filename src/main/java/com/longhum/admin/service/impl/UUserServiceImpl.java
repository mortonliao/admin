package com.longhum.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longhum.admin.dao.user.UUserMapper;
import com.longhum.admin.model.UUser;
import com.longhum.admin.service.UUserService;

@Service
public class UUserServiceImpl implements UUserService{
	
	@Autowired
	private UUserMapper userDao;
	@Override
	public UUser findById(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}
	

}
