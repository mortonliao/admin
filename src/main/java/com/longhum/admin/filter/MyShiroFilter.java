package com.longhum.admin.filter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.longhum.admin.model.SysResource;
import com.longhum.admin.service.SysService;

@Configuration
public class MyShiroFilter {
	
	@Autowired
	private SysService sysService;
	
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean  shiroFilterFacrory(SecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//SecurityUtils.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/user/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		
		/*定义shiro过滤链 Map结构 * Map中key(xml中是指value值)的第一个'/'代表的路径是相对于HttpServletRequest.getContextPath()的值来的 * anon：
		 * 它对应的过滤器里面是空的,什么都没做,这里.do和.jsp后面的*表示参数,比方说login.jsp?main这种 * authc：该过滤器下的页面必须验证后才能访问,
		 * 它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter 
		 * */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/user/logout", "logout");

        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        //filterChainDefinitionMap.put("/login", "anon");//anon 可以理解为不拦截
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/font/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/plug/**", "anon");
        //filterChainDefinitionMap.put("/system/menu_list", "perms[sys:menu:list]");
        
        List<SysResource> list = sysService.findAllMenu();
        if(list != null){
        	for (SysResource sysResource : list) {
        		if(sysResource.getPath() != null && sysResource.getPath().trim().length() > 0){
        			filterChainDefinitionMap.put(sysResource.getPath(), "perms["+sysResource.getPermission()+"]");
        		}
        	}
        }
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        
		return shiroFilterFactoryBean;
	}
	
}
