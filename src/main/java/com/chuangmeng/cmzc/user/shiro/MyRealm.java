package com.chuangmeng.cmzc.user.shiro;

import com.chuangmeng.cmzc.commons.PO.TbUser;
import com.chuangmeng.cmzc.user.dao.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)  throws AuthenticationException   {
        TbUser tbUser = new TbUser();
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String phonenum = token.getUsername();
        char[] pass = token.getPassword();
        String password = new String(pass);
        tbUser.setUserTel(phonenum);
        String shirokey = "com.chuangmeng.cmzc.user.login"+phonenum;
        TbUser user = userMapper.selectUserByTel(tbUser);
        if(redisTemplate.hasKey(shirokey)){
            String logintype = redisTemplate.boundValueOps(shirokey).get();
            //手机登录
            if ("phonenum".equals(logintype)){
                String userPassword = user.getUserPassword();
                /*token=new UsernamePasswordToken(phonenum,userPassword);*/
                token.setPassword(userPassword.toCharArray());
                return new SimpleAuthenticationInfo(phonenum,userPassword,"myrealm");
            }
        }
        if (user == null||!user.getUserPassword().equals(password)) {
            throw new UnknownAccountException();
        }
      //  SecurityUtils.getSubject().getSession().setAttribute("user",user);
        return new SimpleAuthenticationInfo(phonenum,password,"myrealm");
    }
}
