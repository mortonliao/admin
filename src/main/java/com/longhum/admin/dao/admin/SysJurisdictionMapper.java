package com.longhum.admin.dao.admin;

import org.apache.ibatis.annotations.Mapper;

import com.longhum.admin.model.SysJurisdiction;
@Mapper
public interface SysJurisdictionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysJurisdiction record);

    int insertSelective(SysJurisdiction record);

    SysJurisdiction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysJurisdiction record);

    int updateByPrimaryKey(SysJurisdiction record);
}