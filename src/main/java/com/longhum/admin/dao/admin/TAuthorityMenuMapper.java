package com.longhum.admin.dao.admin;

import com.longhum.admin.model.TAuthorityMenu;

public interface TAuthorityMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAuthorityMenu record);

    int insertSelective(TAuthorityMenu record);

    TAuthorityMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAuthorityMenu record);

    int updateByPrimaryKey(TAuthorityMenu record);
}