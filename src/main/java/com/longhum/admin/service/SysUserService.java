package com.longhum.admin.service;

import com.longhum.admin.model.SysUser;

public interface SysUserService {
	SysUser findById(Long id);

	SysUser findByUsername(String username);

}
