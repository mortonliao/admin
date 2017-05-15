package com.longhum.admin.dao.admin;

import com.longhum.admin.model.TAuthorityPageElement;

public interface TAuthorityPageElementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAuthorityPageElement record);

    int insertSelective(TAuthorityPageElement record);

    TAuthorityPageElement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAuthorityPageElement record);

    int updateByPrimaryKey(TAuthorityPageElement record);
}