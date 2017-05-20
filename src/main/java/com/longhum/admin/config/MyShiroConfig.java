package com.longhum.admin.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.longhum.admin.shiro.UserRealm;

@Configuration
public class MyShiroConfig {

	@Bean  
    public HashedCredentialsMatcher hashedCredentialsMatcher(){  
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();  
          
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;  
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));  
          
        return hashedCredentialsMatcher;  
    }  
	
	@Bean
    public SecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        //注入缓存管理器;
       //securityManager.setCacheManager(cacheManager());//这个如果执行多次，也是同样的一个对象;
        return securityManager;
    }
	
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost("");
		redisManager.setPort(3306);
		redisManager.setExpire(1800);// 配置过期时间
		// redisManager.setTimeout(timeout);
		// redisManager.setPassword(password);
		return redisManager;
	}
	
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}
	/**
	 * shiro session的管理
	 */
	public DefaultWebSessionManager SessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		//sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}
	@Bean  
    public UserRealm userRealm(){  
		UserRealm userRealm = new UserRealm();//这里实例化一个我们自己写的MyShiroRealm类  
		userRealm.setCredentialsMatcher(hashedCredentialsMatcher());;  
        return userRealm;  
    }  
	
	/**
     * Shiro生命周期处理器 * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    
    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能 * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
