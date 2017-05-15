package com.longhum.admin.dao.admin;

import com.longhum.admin.model.TUserGroupRole;

public interface TUserGroupRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserGroupRole record);

    int insertSelective(TUserGroupRole record);

    TUserGroupRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserGroupRole record);

    int updateByPrimaryKey(TUserGroupRole record);
}