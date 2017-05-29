package com.longhum.admin.dao;

import java.util.List;

import com.longhum.admin.model.SysUser;

public interface SysUserMapper {
    int delete(Long id);

    SysUser findById(Long id);

	SysUser findByUsername(String username);

	List<SysUser> findAll();

	void update(SysUser user);

	void save(SysUser user);
}