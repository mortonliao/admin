package com.longhum.admin.dao.admin;

import com.longhum.admin.model.TRoleAuthority;

public interface TRoleAuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRoleAuthority record);

    int insertSelective(TRoleAuthority record);

    TRoleAuthority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TRoleAuthority record);

    int updateByPrimaryKey(TRoleAuthority record);
}