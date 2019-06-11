package com.cqyc.food.shiro;

import com.cqyc.food.comm.MD5Hash;
import com.cqyc.food.domain.Buyer;
import com.cqyc.food.service.IBuyerService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * @Description:
 * @Author:
 * @Date:
 */
public class ShiroRleam extends AuthorizingRealm {

    @Autowired
    private IBuyerService buyerService;

    @Autowired
    private HttpSession session;

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1:把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        //2:从UsernamePasswordToken中取出username
        String username = utoken.getUsername();
        //3:调用数据库的方法，从数据库查询username对应的用户记录
        Buyer buyer = buyerService.userLogin(username);
        if(!buyer.getAccount().equals(username)){
            throw new AuthenticationException("账号输入错误");
        }
        if(!MD5Hash.addSalt(new String(utoken.getPassword()),buyer.getAccount()).equals(buyer.getPassword())){
            throw new IncorrectCredentialsException("密码输入错误");
        }
        session.setAttribute("buyer",buyer);
        //Principal ：认证的实体信息，可以是username，也可以是数据表对应的用户的实体类队形
        Object principal = username;
        //Credential:密码
        Object Credentials = buyer.getPassword();
        String realmName = getName();
        ByteSource bytes = ByteSource.Util.bytes(username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, Credentials, bytes, realmName);
        return info;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

}
