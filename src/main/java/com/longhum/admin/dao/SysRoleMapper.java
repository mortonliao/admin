package com.longhum.admin.dao;

import java.util.List;
import java.util.Set;

import com.longhum.admin.model.SysRole;

public interface SysRoleMapper {
    int delete(Long id);

    int save(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int update(SysRole record);

	Set<String> getRolesByUserId(Long userId);

	List<SysRole> findAll();
}