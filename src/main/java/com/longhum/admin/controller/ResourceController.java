package com.longhum.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longhum.admin.entity.TreeResource;
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
	@RequestMapping("/allresource2")
	@ResponseBody
	public List<TreeResource> allresource2(HttpServletRequest request){
		List<SysResource> list = sysService.findAllMenu();
		List<SysResource> list2 = sysService.findByParentIdAndUserName(null, null,(String)SecurityUtils.getSubject().getPrincipal(), null);
		
		List<TreeResource> result = new ArrayList<TreeResource>();
		for (SysResource sysResource : list) {
			TreeResource r = new TreeResource();
			BeanUtils.copyProperties(sysResource, r);
			if(list2.contains(sysResource)){
				r.setChecked(true);
			}
			result.add(r);
		}
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,SysResource resource){
		System.out.println(resource);
		resource = sysService.saveResource(resource);
		return "操作成功";
	}
}
