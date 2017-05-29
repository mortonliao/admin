package com.longhum.admin.filter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;

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
		shiroFilterFactoryBean.setLoginUrl("/user/login.do");
		shiroFilterFactoryBean.setSuccessUrl("/");
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		
		/*定义shiro过滤链 Map结构 * Map中key(xml中是指value值)的第一个'/'代表的路径是相对于HttpServletRequest.getContextPath()的值来的 * anon：
		 * 它对应的过滤器里面是空的,什么都没做,这里.do和.jsp后面的*表示参数,比方说login.jsp?main这种 * authc：该过滤器下的页面必须验证后才能访问,
		 * 它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter 
		 * */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/user/logout.do", "logout");

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
        			if(!sysResource.getPath().startsWith("/")){
        				filterChainDefinitionMap.put("/"+sysResource.getPath()+".do", "perms["+sysResource.getPermission()+"]");
        			}else{
        				filterChainDefinitionMap.put(sysResource.getPath()+".do", "perms["+sysResource.getPermission()+"]");
        			}
        		}
        	}
        }
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        
		return shiroFilterFactoryBean;
	}
	
	@Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                dispatcherServlet);
        registration.getUrlMappings().clear();
        registration.addUrlMappings("/");
        registration.addUrlMappings("*.do");
        registration.addUrlMappings("*.css");
        registration.addUrlMappings("*.js");
        registration.addUrlMappings("*.png");
        registration.addUrlMappings("*.jpg");
        registration.addUrlMappings("*.jpeg");
        registration.addUrlMappings("*.gif");
        
        return registration;
    }
	
//	@Bean
//	public CommonsMultipartResolver multipartResolver(){
//		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//		commonsMultipartResolver.setDefaultEncoding("UTF-8");
//		commonsMultipartResolver.setMaxUploadSize(1024000);
//		return commonsMultipartResolver;
//	}
	
	//显示声明CommonsMultipartResolver为mutipartResolver
    @Bean(name = "multipartResolver")
       public MultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSize(50*1024*1024);//上传文件大小 50M 50*1024*1024
        return resolver;
    }   
}
