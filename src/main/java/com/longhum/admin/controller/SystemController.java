package com.longhum.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longhum.admin.dao.admin.TMenuMapper;
import com.longhum.admin.domain.ResourceMenu;
import com.longhum.admin.model.TMenu;
/**
 * @author liaoxiaohu
 * @date 2017年5月14日
 * @info
 */
@Controller
@RequestMapping("/system")

public class SystemController {
	
	@Autowired
	private TMenuMapper menuDao;
	
	@RequestMapping("/menu_list")
	@ResponseBody
	//@RequiresPermissions("sys:manager")//权限管理;
	public List<ResourceMenu> menulist(HttpServletRequest request,Integer id){
		String ctx = request.getContextPath();
		List<TMenu> list = menuDao.findByPid(id);
		
//		List<ResourceMenu> list = new ArrayList<ResourceMenu>();
//		ResourceMenu menu1 = new ResourceMenu(5,1,"会员中心");
//		menu1.setOpen(true);
//		menu1.setParent(true);
//		menu1.setIcon(ctx+"/images/ico/url.gif");
//		ResourceMenu menu4 = new ResourceMenu(6,5,"所有会员");
//		menu4.setIcon(ctx+"/images/ico/2.gif");
//		menu4.setPath("http://www.baidu.com");
//		ResourceMenu menu5 = new ResourceMenu(7,5,"在线会员");
//		menu5.setIcon(ctx+"/images/ico/2.gif");
//		menu5.setPath("http://www.qq.com");
//		ResourceMenu menu6 = new ResourceMenu(8,5,"异常会员");
//		menu6.setIcon(ctx+"/images/ico/2.gif");
//		menu6.setPath("http://www.360.com");
//		ResourceMenu menu2 = new ResourceMenu(9,1,"添加会员");
//		menu2.setParent(true);
//		menu2.setIcon(ctx+"/images/ico/url.gif");
//		list.add(menu1);
//		list.add(menu2);
//		list.add(menu4);
//		list.add(menu5);
//		list.add(menu6);
		
		return null;
	}
}
