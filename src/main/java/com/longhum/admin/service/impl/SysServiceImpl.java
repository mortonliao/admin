package com.longhum.admin.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longhum.admin.dao.SysResourceMapper;
import com.longhum.admin.dao.SysRoleMapper;
import com.longhum.admin.dao.SysRoleResourceMapper;
import com.longhum.admin.dao.SysUserMapper;
import com.longhum.admin.model.SysResource;
import com.longhum.admin.model.SysRole;
import com.longhum.admin.model.SysRoleResource;
import com.longhum.admin.service.SysService;;
@Service
public class SysServiceImpl implements SysService{

	@Autowired
	private SysRoleResourceMapper roleResourceDao;
	@Autowired
	private SysResourceMapper resourceDao;
	@Autowired
	private SysUserMapper userDao;
	@Autowired
	private SysRoleMapper roleDao;
	
	@Override
	public List<SysResource> findByUserNameOrParentIdOrPreateIdsOrType(Integer parentId,String parentIds,String username,String type) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parentId", parentId);
		map.put("parentIds", parentIds);
		map.put("type", type);
		map.put("roleId", userDao.findByUsername(username).getRoleId());
//		if(parentId != null && parentIds != null){
//			map.put("parentIds", parentIds+parentId+"/");
//		}
		return resourceDao.findByRoleIdOrParentIdOrPreateIdsOrType(map);
	}

	@Override
	public Set<String> getRolesByUserId(Long userId) {
		return roleDao.getRolesByUserId(userId);
	}

	@Override
	public Set<String> getPermissionsByUserId(Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleId", userDao.findById(userId).getRoleId());
		//List<SysResource> list = resourceDao.findByParentIdAndUserId(map);
		List<SysResource> list = resourceDao.findByRoleIdOrParentIdOrPreateIdsOrType(map);
		Set<String> result = new HashSet<String>();
		for (SysResource r : list) {
			if(r.getPermission() != null && r.getPermission().trim().length() > 0){
				result.add(r.getPermission());
			}
		}
		return result;
	}

	@Override
	public List<SysResource> findAllMenu() {
		return resourceDao.findAllMenu();
	}

//	@Override
//	public List<SysResource> findFirstByParentIdAndUserName(int parentId, String username) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("parentId", parentId);
//		map.put("userId", userDao.findByUsername(username).getId());
//		return resourceDao.findByRoleIdAndParentIdOrPreateIdsOrType(map);
//	}

	@Override
	public SysResource saveResource(SysResource resource) {
		
		if(resource == null){
			return null;
		}else if(resource.getId() == null){
			resourceDao.save(resource);
		}else{
			resourceDao.update(resource);
		}
		return resource;
	}

	@Override
	public void saveSysRoleResourceList(List<SysRoleResource> roleResourcesList) {
		if(roleResourcesList == null || roleResourcesList.size() ==  0){
			return;
		}
		roleResourceDao.saveList(roleResourcesList);
	}

	@Override
	public void deleteSysRoleResourceList(Long roleId, List<Long> list) {
		if(roleId == null || list == null || list.size() == 0){
			return;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleId", roleId);
		map.put("list", list);
		roleResourceDao.deleteSysRoleResourceList(map);
	}

	@Override
	public void saveSysRole(SysRole role) {
		if(role.getId() != null){
			roleDao.update(role);
		}else{
			roleDao.save(role);
		}
	}

	@Override
	public SysRole findRoleById(Long roleId) {
		return roleDao.findById(roleId);
	}

	@Override
	public List<SysResource> findByRoleId(Long roleId) {
		return resourceDao.findByRoleId(roleId);
	}

}
