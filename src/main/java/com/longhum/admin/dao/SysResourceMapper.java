package com.longhum.admin.dao;

import java.util.List;
import java.util.Map;

import com.longhum.admin.model.SysResource;

public interface SysResourceMapper {
    int deleteByPrimaryKey(Long id);

    int save(SysResource record);

    SysResource findById(Long id);

	List<SysResource> findByParentIdAndUserId(Map<String,Object> map);

	List<SysResource> findAllMenu();

	List<SysResource> findByRoleIdOrParentIdOrPreateIdsOrType(Map<String, Object> map);

	void update(SysResource resource);

	List<SysResource> findByRoleId(Long roleId);

}