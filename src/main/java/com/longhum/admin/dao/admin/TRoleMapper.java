package com.longhum.admin.dao.admin;

import java.util.Set;

import com.longhum.admin.model.TRole;

public interface TRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRole record);

    int insertSelective(TRole record);

    TRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TRole record);

    int updateByPrimaryKey(TRole record);
    Set<String> getRolesByUserId(Integer userId);

}