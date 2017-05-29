package com.longhum.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.longhum.admin.entity.ResultSimpleDate;
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
	
	@RequestMapping("/manager.do")
	public String menuList(HttpServletRequest request,ModelMap map){
		return "/system/resource";
	}
	@RequestMapping("/allResource.do")
	@ResponseBody
	public List<SysResource> allResource(HttpServletRequest request){
		List<SysResource> list = sysService.findAllMenu();
		if(list != null){
			for (SysResource sr : list) {
				if(sr.getIcon() != null ){
					sr.setIcon(request.getContextPath()+sr.getIcon());
				}
			}
		}
		return list;
	}
	@RequestMapping("/allResource2.do")
	@ResponseBody
	public List<TreeResource> allresource2(HttpServletRequest request){
		List<SysResource> list = sysService.findAllMenu();
		List<SysResource> list2 = sysService.findByUserNameOrParentIdOrPreateIdsOrType(null,null,(String)SecurityUtils.getSubject().getPrincipal(), null);
		
		List<TreeResource> result = new ArrayList<TreeResource>();
		for (SysResource sysResource : list) {
			TreeResource r = new TreeResource();
			BeanUtils.copyProperties(sysResource, r);
			if(list2.contains(sysResource)){
				r.setChecked(true);
			}
			if(r.getIcon() != null){
				r.setIcon(request.getContextPath()+r.getIcon());
			}
			result.add(r);
		}
		return result;
	}
	@RequestMapping("/allResource3.do")
	@ResponseBody
	public List<TreeResource> allresource3(HttpServletRequest request,Long roleId){
		List<SysResource> list = sysService.findAllMenu();
		List<SysResource> list2 = sysService.findByRoleId(roleId);
		
		List<TreeResource> result = new ArrayList<TreeResource>();
		for (SysResource sysResource : list) {
			TreeResource r = new TreeResource();
			BeanUtils.copyProperties(sysResource, r);
			if(list2.contains(sysResource)){
				r.setChecked(true);
			}
			if(r.getIcon() != null){
				r.setIcon(request.getContextPath()+r.getIcon());
			}
			result.add(r);
		}
		return result;
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	/**
	 * 
	 * 
	 * @author liaoxiaohu
	 * @date 2017年5月28日
	 * @param request
	 * @param resource
	 * @return@RequestParam(name="file",required=true) MultipartFile file
	 * ,@RequestParam(value="iconFile",required=false) MultipartFile iconFile
	 */
	public ResultSimpleDate save(HttpServletRequest request,SysResource resource,@RequestParam(value="iconFile",required=false) MultipartFile iconFile){
		//int x = 1/0;
		//MultipartFile file = resource.getFile();
		if(iconFile != null){
			String[] type = iconFile.getContentType().split("/");
			if(type[0].equals("image")){
				try {
					String fileName = "/img/icon/reousrce_"+System.currentTimeMillis()+"."+type[1];
					resource.setIcon(fileName);
					iconFile.transferTo(new File("/usr/local/var/www"+fileName));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}else{
				return ResultSimpleDate.error("不是有效文件");
			}
		}
		sysService.saveResource(resource);
		return ResultSimpleDate.ok("操作成功", resource);
	}
	@RequestMapping("/saveIcon.do")
	@ResponseBody
	/**
	 * 
	 * 
	 * @author liaoxiaohu
	 * @date 2017年5月28日
	 * @param request
	 * @param resource
	 * @return@RequestParam(name="file",required=true) MultipartFile file
	 */
	public ResultSimpleDate saveIcon(HttpServletRequest request){
		
		return ResultSimpleDate.ok("操作成功");
	}
	
}

