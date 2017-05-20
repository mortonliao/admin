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
import com.longhum.admin.dao.SysUserMapper;
import com.longhum.admin.model.SysResource;
import com.longhum.admin.service.SysService;;
@Service
public class SysServiceImpl implements SysService{

	@Autowired
	private SysResourceMapper resourceDao;
	@Autowired
	private SysUserMapper userDao;
	@Autowired
	private SysRoleMapper roleDao;
	
	@Override
	public List<SysResource> findByParentIdAndUserName(Integer parentId, String username) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parentId", parentId);
		map.put("userId", userDao.findByUsername(username).getId());
		return resourceDao.findByParentIdAndUserId(map);
	}

	@Override
	public Set<String> getRolesByUserId(Long userId) {
		return roleDao.getRolesByUserId(userId);
	}

	@Override
	public Set<String> getResourceByUserId(Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		List<SysResource> list = resourceDao.findByParentIdAndUserId(map);
		Set<String> result = new HashSet<String>();
		for (SysResource r : list) {
			result.add(r.getName());
		}
		return result;
	}

	@Override
	public List<SysResource> findAllMenu() {
		return resourceDao.findAllMenu();
	}

	@Override
	public List<SysResource> findFirstByParentIdAndUserName(int parentId, String username) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parentId", parentId);
		map.put("userId", userDao.findByUsername(username).getId());
		return resourceDao.findFirstByParentIdAndUserId(map);
	}

}
