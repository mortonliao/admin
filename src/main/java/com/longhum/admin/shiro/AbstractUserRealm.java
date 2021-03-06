package com.longhum.admin.shiro;

import java.util.HashSet;
import java.util.Set;

//import org.apache.catalina.realm.JNDIRealm.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.longhum.admin.model.SysUser;
import com.longhum.admin.service.SysUserService;

public abstract class AbstractUserRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(AbstractUserRealm.class);

    @Autowired
    private SysUserService userService;
    //获取用户组的权限信息
    //public abstract UserRolesAndPermissions doGetGroupAuthorizationInfo(TUser user);
    //获取用户角色的权限信息
    public abstract UserRolesAndPermissions doGetRoleAuthorizationInfo(SysUser user);

    /**
     * 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String currentLoginName = (String) principals.getPrimaryPrincipal();
        Set<String> userRoles = new HashSet<String>();
        Set<String> userPermissions = new HashSet<String>();
        //从数据库中获取当前登录用户的详细信息
        SysUser userInfo = userService.findByUsername(currentLoginName);
        if (null != userInfo) {
            //UserRolesAndPermissions groupContainer = doGetGroupAuthorizationInfo(userInfo);
            UserRolesAndPermissions roleContainer = doGetRoleAuthorizationInfo(userInfo);
            //userRoles.addAll(groupContainer.geSysUserRoles());
            userRoles.addAll(roleContainer.getUserRoles());
            //userPermissions.addAll(groupContainer.getUserPermissions());
            userPermissions.addAll(roleContainer.getUserPermissions());
        } else {
            throw new AuthorizationException();
        }
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(userRoles);
        authorizationInfo.addStringPermissions(userPermissions);
        logger.info("###【获取角色成功】[SessionId] => {}", SecurityUtils.getSubject().getSession().getId());
        return authorizationInfo;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //查出是否有此用户
        SysUser user = userService.findByUsername(token.getUsername());
        if (user != null) {
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), 
            		ByteSource.Util.bytes(user.generateSalt()),getName());
        
        }
        return null;
    }
    
    protected class UserRolesAndPermissions {
        Set<String> userRoles;
        Set<String> userPermissions;

        public UserRolesAndPermissions(Set<String> userRoles, Set<String> userPermissions) {
            this.userRoles = userRoles;
            this.userPermissions = userPermissions;
        }

        public Set<String> getUserRoles() {
            return userRoles;
        }

        public Set<String> getUserPermissions() {
            return userPermissions;
        }
    }
}