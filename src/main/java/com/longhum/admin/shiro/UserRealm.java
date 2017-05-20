package com.longhum.admin.shiro;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.longhum.admin.model.SysUser;
import com.longhum.admin.service.SysService;

@Component
public class UserRealm extends AbstractUserRealm {

	@Autowired
	private SysService sysService;
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
    public UserRolesAndPermissions doGetRoleAuthorizationInfo(SysUser user) {
        Set<String> userRoles = new HashSet<String>();
        Set<String> userResources = new HashSet<String>();
        //获取当前用户下拥有的所有角色列表,及权限
        userRoles = sysService.getRolesByUserId(user.getId());
        userResources =  sysService.getResourceByUserId(user.getId());
        return new UserRolesAndPermissions(userRoles, userResources);
    }
   
}
