package com.longhum.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.longhum.admin.model.SysResource;
import com.longhum.admin.service.SysService;

/**
 * @author liaoxiaohu
 * @date 2017年5月14日
 * @info
 */
@Controller
public class HomeController {
	
	@Autowired
	private SysService sysService;
	
	@RequestMapping("/")
    public ModelAndView index(ModelMap map,HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		List<SysResource> list = sysService.findByUserNameOrParentIdOrPreateIdsOrType(1,null,(String)subject.getPrincipal(),"menu");
		if(list != null){
			for (SysResource sr : list) {
				if(sr.getIcon() != null ){
					sr.setIcon(request.getContextPath()+sr.getIcon());
				}
			}
		}
		map.put("menuList", list);
		
        return new ModelAndView("index");
    }
	@RequestMapping("/403")
	public ModelAndView page403(ModelMap map,HttpServletRequest request){
		return new ModelAndView("page403");
	}
}
