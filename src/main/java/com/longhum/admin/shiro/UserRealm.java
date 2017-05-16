package com.longhum.admin.shiro;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.longhum.admin.model.TUser;
import com.longhum.admin.service.UserService;

@Component
public class UserRealm extends AbstractUserRealm {

	@Autowired
	private UserService userService;
//    @Override
//    public UserRolesAndPermissions doGetGroupAuthorizationInfo(TUser user) {
//        Set<String> userRoles = new HashSet<String>();
//        Set<String> userPermissions = new HashSet<String>();
//        //获取当前用户所属用户组拥有的所有角色列表,及权限
//        userRoles = userService.getGroupRolesByUserId(user.getId());
//        userPermissions =  userService.getGroupPermissionsByUserId(user.getId());
//        return new UserRolesAndPermissions(userRoles, userPermissions);
//    }

    @Override
    public UserRolesAndPermissions doGetRoleAuthorizationInfo(TUser user) {
        Set<String> userRoles = new HashSet<String>();
        Set<String> userPermissions = new HashSet<String>();
        //获取当前用户下拥有的所有角色列表,及权限
        userRoles = userService.getRolesByUserId(user.getId());
        userPermissions =  userService.getPermissionsByUserId(user.getId());
        return new UserRolesAndPermissions(userRoles, userPermissions);
    }
   
}
