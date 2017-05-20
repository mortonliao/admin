package com.longhum.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longhum.admin.model.SysResource;
import com.longhum.admin.service.SysService;
/**
 * @author liaoxiaohu
 * @date 2017年5月14日
 * @info
 */
@Controller
@RequestMapping("/resource")

public class ResourceController {
	@Autowired
	private SysService sysService;
	
	@RequestMapping("/manager")
	public String menulist(HttpServletRequest request,ModelMap map){
		return "/system/resource";
	}
	@RequestMapping("/allresource")
	@ResponseBody
	public List<SysResource> allresource(HttpServletRequest request){
		List<SysResource> list = sysService.findAllMenu();
		return list;
	}
}
