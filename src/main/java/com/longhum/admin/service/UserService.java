package com.longhum.admin.service;

import java.util.Set;

import com.longhum.admin.model.TUser;

public interface UserService {
	TUser findById(Integer id);

	Set<String> getRolesByUserId(Integer userId);

	//Set<String> getGroupRolesByUserId(Integer userId);

	//Set<String> getGroupPermissionsByUserId(Integer id);

	Set<String> getPermissionsByUserId(Integer id);
}
