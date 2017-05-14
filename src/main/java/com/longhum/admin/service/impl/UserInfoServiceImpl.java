package com.longhum.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longhum.admin.dao.member.UserInfoMapper;
import com.longhum.admin.model.UserInfo;
import com.longhum.admin.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	private UserInfoMapper userDao;
	@Override
	public UserInfo findById(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}
	

}
