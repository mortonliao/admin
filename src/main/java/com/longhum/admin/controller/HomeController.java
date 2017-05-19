package com.longhum.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.longhum.admin.domain.ResourceMenu;

/**
 * @author liaoxiaohu
 * @date 2017年5月14日
 * @info
 */
@Controller
public class HomeController {
	
	/*@GetMapping("/login")
	public String loginForm() {
		return "usercenter/login";
	}*/
	
	@RequestMapping("/")
    public ModelAndView login(ModelMap map){
		List<ResourceMenu> list = new ArrayList<ResourceMenu>(); ResourceMenu
		menu1 = new ResourceMenu(1, 0, "会员管理"); ResourceMenu menu2 = new
		ResourceMenu(2, 0, "系统管理"); ResourceMenu menu3 = new ResourceMenu(3,
		0, "报表管理"); ResourceMenu menu4 = new ResourceMenu(4, 0, "监控管理");
		
		list.add(menu1); list.add(menu2); list.add(menu3); list.add(menu4);
		
		map.put("menuList", list);
		
        return new ModelAndView("index");
    }
}
