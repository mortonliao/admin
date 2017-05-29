package com.longhum.admin.service;

import java.util.List;

import com.longhum.admin.model.SysUser;

public interface SysUserService {
	SysUser findById(Long id);

	SysUser findByUsername(String username);

	List<SysUser> findAll();

	void saveUser(SysUser user);

	void deleteUser(Long userId);

}
