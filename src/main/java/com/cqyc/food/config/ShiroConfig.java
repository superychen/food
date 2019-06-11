package com.cqyc.food.config;

import com.cqyc.food.shiro.ShiroRleam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @Description:
 * @Author:
 * @Date:
 */
//@Configuration
@Slf4j
public class ShiroConfig {
    //这里配置CredentialsMatcher
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");//设置MD5算法加密
        matcher.setHashIterations(10);//进行加密的算法次数
        return matcher;
    }

    //配置自定义的权限登录器
    @Bean(name = "shiroRleam")
    public ShiroRleam shiroRleam(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher){
        ShiroRleam shiroRleam = new ShiroRleam();
        shiroRleam.setCredentialsMatcher(hashedCredentialsMatcher);
        return shiroRleam;
    }

    //权限管理，配置主要是Realm的管理认证,核心安全事务管理器
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("shiroRleam")ShiroRleam shiroRleam){
        log.debug("--------加载shiroSecurityManager----------");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //自定义shiroRleam配置到securityManager中
        //securityManager.setSessionManager(sessionManager());
        securityManager.setRealm(shiroRleam);
        return  securityManager;
    }

    //自定义sessionManager
    /*@Bean
    public SessionManager sessionManager(){
        MySessionManager mySessionManager = new MySessionManager();
        return mySessionManager;
    }*/

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/buyer/unauth");
        //配置访问权限，相当于xml中的filter
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/food/*","anon");
        map.put("/buyer/login","anon");
        map.put("/buyer/isLogin","anon");
        map.put("/buyer/register","anon");
        map.put("/comment/commentCount","anon");
        map.put("/**","authc");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
