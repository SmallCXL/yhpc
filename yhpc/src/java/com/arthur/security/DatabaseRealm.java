package com.arthur.security;

import com.arthur.pojo.User;
import com.arthur.service.intf.PermissionService;
import com.arthur.service.intf.RoleService;
import com.arthur.service.intf.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class DatabaseRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String principal = ((String) principalCollection.getPrimaryPrincipal());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //通过角色获取相关的授权，不需要额外添加权限信息
        Set<String> roles = roleService.getRoleNames(principal);
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CustomTypeToken token = ((CustomTypeToken) authenticationToken);
        String phoneNumber = token.getPrincipal().toString();
        String passwordDB = userService.getCredentials(phoneNumber);
        User user = userService.getUser(phoneNumber);
        String salt = user.getSalt_();
        return new SimpleAuthenticationInfo(phoneNumber, passwordDB, ByteSource.Util.bytes(salt), getName());
    }
}
