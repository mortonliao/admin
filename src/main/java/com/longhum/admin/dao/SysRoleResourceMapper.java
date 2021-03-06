package com.longhum.admin.dao;

import java.util.List;
import java.util.Map;

import com.longhum.admin.model.SysRoleResource;

public interface SysRoleResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleResource record);

    int insertSelective(SysRoleResource record);

    SysRoleResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleResource record);

    int updateByPrimaryKey(SysRoleResource record);

	void saveList(List<SysRoleResource> list);

	void deleteSysRoleResourceList(Map<String, Object> map);
}