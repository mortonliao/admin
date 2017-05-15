package com.longhum.admin.dao.admin;

import com.longhum.admin.model.TUserGroupUser;

public interface TUserGroupUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserGroupUser record);

    int insertSelective(TUserGroupUser record);

    TUserGroupUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserGroupUser record);

    int updateByPrimaryKey(TUserGroupUser record);
}