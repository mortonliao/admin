package com.longhum.admin.dao.user;

import com.longhum.admin.model.UUser;

public interface UUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UUser record);

    int insertSelective(UUser record);

    UUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);
}