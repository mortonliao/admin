package com.longhum.admin.dao.admin;

import com.longhum.admin.model.TUserGroup;

public interface TUserGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserGroup record);

    int insertSelective(TUserGroup record);

    TUserGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserGroup record);

    int updateByPrimaryKey(TUserGroup record);
}