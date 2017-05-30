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

import com.longhum.admin.entity.ResultSimpleDate;
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
	
	@RequestMapping("/menuList.do")
	@ResponseBody
	public List<SysResource> menuList(HttpServletRequest request,Integer id,String preantIds){
		List<SysResource> list = sysService.findByUserNameOrParentIdOrPreateIdsOrType(id, preantIds,(String)SecurityUtils.getSubject().getPrincipal(),"menu");
		if(list != null){
			for (SysResource sr : list) {
				if(sr.getIcon() != null ){
					sr.setIcon(request.getContextPath()+sr.getIcon());
				}
			}
		}
		return list;
	}
	
	@RequestMapping("/permission.do")
	public String permission(HttpServletRequest request,ModelMap map){
		List<SysRole> list = userRoleService.findAllRole();
		map.put("list", list);
		return "system/permission";
	}
	
	@RequestMapping("/role.do")
	public String role(HttpServletRequest request,ModelMap map){
		List<SysRole> list = userRoleService.findAllRole();
		map.put("list", list);
		return "system/role";
	}
	
	@RequestMapping("/deleteRole.do")
	public String deleteRole(HttpServletRequest request,ModelMap map,Long roleId){
		userRoleService.deleteRoleById(roleId);
		List<SysRole> list = userRoleService.findAllRole();
		map.put("list", list);
		return "system/permission";
	}
	
	@RequestMapping("/roleList.do")
	@ResponseBody
	public List<SysRole> roleList(HttpServletRequest request,Integer id){
		List<SysRole> list = userRoleService.findAllRole();
		return list;
	}
	
	@RequestMapping("/saveRole.do")
	public String saveRole(HttpServletRequest request,ModelMap map,SysRole role){
		sysService.saveSysRole(role);
		List<SysRole> list = userRoleService.findAllRole();
		map.put("list", list);
		return "system/permission";
	}
	@RequestMapping("/findRoleById.do")
	@ResponseBody
	public ResultSimpleDate findRoleById(HttpServletRequest request, Long roleId){
		SysRole role = sysService.findRoleById(roleId);
		return ResultSimpleDate.ok("查询成功",role);
	}
	
	@RequestMapping("/saveRolePermission.do")
	@ResponseBody
	public String saveRolePermission(HttpServletRequest request,@RequestParam("roleId") Long roleId ,
			@RequestParam(name="addList[]",required=false) List<Long> addList,@RequestParam(name="removeList[]",required=false) List<Long> removeList){
		List<SysResource> list = sysService.findByUserNameOrParentIdOrPreateIdsOrType(null, null,(String)SecurityUtils.getSubject().getPrincipal(), null);
		List<SysRoleResource> roleResourcesList = new ArrayList<SysRoleResource>();
		for (Long id : addList) {
			SysResource r = new SysResource();
			r.setId(Long.valueOf(id));
			if(!list.contains(r)){
				SysRoleResource rr = new SysRoleResource();
				rr.setResourceId(id.hashCode());
				rr.setRoleId(roleId.intValue());
				roleResourcesList.add(rr);
			}
		}
		
		sysService.saveSysRoleResourceList(roleResourcesList);
		
		sysService.deleteSysRoleResourceList(roleId,removeList);
		
		return "操作成功";
	}
	@RequestMapping("/saveRolePermission2.do")
	@ResponseBody
	public ResultSimpleDate saveRolePermission2(HttpServletRequest request,@RequestParam("roleId") Long roleId ,
			@RequestParam(name="addList[]",required=false) List<Long> addList,@RequestParam(name="removeList[]",required=false) List<Long> removeList){
		List<SysResource> list = sysService.findByRoleId(roleId);
		List<SysRoleResource> roleResourcesList = new ArrayList<SysRoleResource>();
		for (Long id : addList) {
			SysResource r = new SysResource();
			r.setId(Long.valueOf(id));
			if(!list.contains(r)){
				SysRoleResource rr = new SysRoleResource();
				rr.setResourceId(id.intValue());
				rr.setRoleId(roleId.intValue());
				roleResourcesList.add(rr);
			}
		}
		
		sysService.saveSysRoleResourceList(roleResourcesList);
		
		sysService.deleteSysRoleResourceList(roleId,removeList);
		return ResultSimpleDate.ok("修改成功");
	}
	
}
