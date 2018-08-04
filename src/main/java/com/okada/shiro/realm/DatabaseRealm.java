package com.okada.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class DatabaseRealm extends AuthorizingRealm {

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = principals.getPrimaryPrincipal().toString();
        Set<String> roles = new HashSet<>();
        if ("admin".equals(username)) {
            roles.add("admin");
        } else {
            roles.add("user");
        }
        return  new SimpleAuthorizationInfo(roles);
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1. 把 AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        // 2. 从 UsernamePasswordToken 获取 username
        String username = upToken.getUsername();
        // 3. 调用数据的方法，从数据库中查询 username 对应的记录
        // 4. 若用户不存在，则抛出 UnknownAccountException 异常
        // 5. 根据用户信息的情况，决定是否需要抛出其他 AuthenticationException 异常
        // 6. 根据用户信息的情况，构建 AuthenticationInfo 并返回。通常使用的实现类为：SimpleAuthenticationInfo
        // 以下信息是用数据库中获取的
        // 1. principal：认证的实体信息，可以是 username，也可以使用数据表对应的用户实体对象
        // 2. credentials：密码
        // 3. realmName：当前 realm 对象的 name，调用父类的 getName() 即可
        // 4. 盐值

        String passwordInDb = new SimpleHash("MD5", "123456", "salt", 1024).toString();  // 数据库中存放的密码
        // 把数据库中存放的用户名和密码传回去
        return new SimpleAuthenticationInfo(username, passwordInDb, getName());
    }
}
