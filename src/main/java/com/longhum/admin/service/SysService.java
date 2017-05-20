package com.longhum.admin.service;

import java.util.List;
import java.util.Set;

import com.longhum.admin.model.SysResource;

public interface SysService {

	Set<String> getRolesByUserId(Long userId);

	Set<String> getResourceByUserId(Long id);

	List<SysResource> findByParentIdAndUserName(Integer parentId, String username);

	List<SysResource> findAllMenu();

	List<SysResource> findFirstByParentIdAndUserName(int i, String principal);
}
