package com.longhum.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/system")

public class SystemController {
	@Autowired
	private SysService sysService;
	
	@RequestMapping("/menu_list")
	@ResponseBody
	//@RequiresPermissions("sys:manager")//权限管理;
	public List<SysResource> menulist(HttpServletRequest request,Integer id){
		return sysService.findByParentIdAndUserName(id, (String)SecurityUtils.getSubject().getPrincipal());
	}
}
