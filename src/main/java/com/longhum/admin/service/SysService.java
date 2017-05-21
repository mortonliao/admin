package com.longhum.admin.service;

import java.util.List;
import java.util.Set;

import com.longhum.admin.model.SysResource;
import com.longhum.admin.model.SysRoleResource;

public interface SysService {

	Set<String> getRolesByUserId(Long userId);

	Set<String> getPermissionsByUserId(Long id);

	List<SysResource> findByParentIdAndUserName(Integer parentId,String parentIds, String username,String type);

	List<SysResource> findAllMenu();

	List<SysResource> findFirstByParentIdAndUserName(int i, String principal);

	SysResource saveResource(SysResource resource);

	void saveSysRoleResourceList(List<SysRoleResource> roleResourcesList);

	void deleteSysRoleResourceList(Integer roleId, List<Integer> removeList);
}
