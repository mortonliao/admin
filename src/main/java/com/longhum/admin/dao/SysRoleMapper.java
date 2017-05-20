package com.longhum.admin.dao;

import java.util.Set;

import com.longhum.admin.model.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

	Set<String> getRolesByUserId(Long userId);
}