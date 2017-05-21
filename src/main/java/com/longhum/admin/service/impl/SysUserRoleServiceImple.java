package com.longhum.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longhum.admin.dao.SysRoleMapper;
import com.longhum.admin.model.SysRole;
import com.longhum.admin.service.SysUserRoleService;

@Service
public class SysUserRoleServiceImple implements SysUserRoleService{

	@Autowired
	private SysRoleMapper roleDao;
	
	@Override
	public List<SysRole> findAllRole() {
		return roleDao.findAll();
	}

}
