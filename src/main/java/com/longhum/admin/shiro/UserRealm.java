package com.longhum.admin.shiro;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.longhum.admin.model.TUser;

@Component
public class UserRealm extends AbstractUserRealm {

    @Override
    public UserRolesAndPermissions doGetGroupAuthorizationInfo(TUser user) {
        Set<String> userRoles = new HashSet<String>();
        Set<String> userPermissions = new HashSet<String>();
        //TODO 获取当前用户下拥有的所有角色列表,及权限
        return new UserRolesAndPermissions(userRoles, userPermissions);
    }

    @Override
    public UserRolesAndPermissions doGetRoleAuthorizationInfo(TUser user) {
        Set<String> userRoles = new HashSet<String>();
        Set<String> userPermissions = new HashSet<String>();
        //TODO 获取当前用户下拥有的所有角色列表,及权限
        return new UserRolesAndPermissions(userRoles, userPermissions);
    }
}
