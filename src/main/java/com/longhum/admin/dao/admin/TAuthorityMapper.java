package com.longhum.admin.dao.admin;

import java.util.Set;

import com.longhum.admin.model.TAuthority;

public interface TAuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAuthority record);

    int insertSelective(TAuthority record);

    TAuthority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAuthority record);

    int updateByPrimaryKey(TAuthority record);
    
    Set<String> getPermissionsByUserId(Integer userId);
}