package com.longhum.admin.dao;

import java.util.List;
import java.util.Map;

import com.longhum.admin.model.SysResource;

public interface SysResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    SysResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);

	//Set<String> getResourceByUserId(Long userId);

	List<SysResource> findByParentIdAndUserId(Map<String,Object> map);


	List<SysResource> findAllMenu();

	List<SysResource> findFirstByParentIdAndUserId(Map<String, Object> map);

}