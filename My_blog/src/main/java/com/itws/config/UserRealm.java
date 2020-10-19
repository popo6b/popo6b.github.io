package com.itws.config;

import com.itws.pojo.User;
import com.itws.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        if(authenticationToken.getPrincipal()==null){
            return null;
        }
        String name = authenticationToken.getPrincipal().toString();
        User user = userService.queryUserByName(name);
        if(user==null){
            return null;
        }else{
            Subject currentSubject= SecurityUtils.getSubject();
            Session session = currentSubject.getSession();
            session.setAttribute("user",user);
            session.setTimeout(24 * 60 * 60 * 1000L);
            //盐值
            ByteSource credentialsSalt=ByteSource.Util.bytes(user.getUsername());
            return new SimpleAuthenticationInfo(name,user.getPassword(),credentialsSalt,getName());
        }
    }
}
