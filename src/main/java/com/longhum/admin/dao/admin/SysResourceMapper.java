package com.longhum.admin.dao.admin;

import org.apache.ibatis.annotations.Mapper;

import com.longhum.admin.model.SysResource;
@Mapper
public interface SysResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    SysResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);
}