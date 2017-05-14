package com.longhum.admin.dao.admin;

import org.apache.ibatis.annotations.Mapper;

import com.longhum.admin.model.SysRole;
@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}