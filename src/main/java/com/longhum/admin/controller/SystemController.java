package com.longhum.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longhum.admin.entity.TreeResource;
import com.longhum.admin.model.SysResource;
import com.longhum.admin.model.SysRole;
import com.longhum.admin.model.SysRoleResource;
import com.longhum.admin.service.SysService;
import com.longhum.admin.service.SysUserRoleService;
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
	@Autowired
	private SysUserRoleService userRoleService;
	
	@RequestMapping("/menuList")
	@ResponseBody
	public List<SysResource> menuList(HttpServletRequest request,Integer id,String preantIds){
		List<SysResource> list = sysService.findByParentIdAndUserName(id, preantIds, (String)SecurityUtils.getSubject().getPrincipal(),"menu");
		return list;
	}
	
	@RequestMapping("/permission")
	public String permission(HttpServletRequest request,ModelMap map){
		List<SysRole> list = userRoleService.findAllRole();
		map.put("list", list);
		return "system/permission";
	}
	
	@RequestMapping("/role")
	public String role(HttpServletRequest request,ModelMap map){
		List<SysRole> list = userRoleService.findAllRole();
		map.put("list", list);
		return "system/role";
	}
	
	@RequestMapping("/roleList")
	@ResponseBody
	public List<SysRole> roleList(HttpServletRequest request,Integer id){
		List<SysRole> list = userRoleService.findAllRole();
		return list;
	}
	@RequestMapping("/saveRolePermission")
	@ResponseBody
	public String saveRolePermission(HttpServletRequest request,@RequestParam("roleId") Integer roleId ,
			@RequestParam(name="addList[]",required=false) List<Integer> addList,@RequestParam(name="removeList[]",required=false) List<Integer> removeList){
		List<SysResource> list = sysService.findByParentIdAndUserName(null, null,(String)SecurityUtils.getSubject().getPrincipal(), null);
		List<SysRoleResource> roleResourcesList = new ArrayList<SysRoleResource>();
		for (Integer id : addList) {
			SysResource r = new SysResource();
			r.setId(Long.valueOf(id));
			if(!list.contains(r)){
				SysRoleResource rr = new SysRoleResource();
				rr.setResourceId(id);
				rr.setRoleId(roleId);
				roleResourcesList.add(rr);
			}
		}
		
		sysService.saveSysRoleResourceList(roleResourcesList);
		
		sysService.deleteSysRoleResourceList(roleId,removeList);
		
		return "操作成功";
	}
	
}
