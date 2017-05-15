package com.longhum.admin.dao.admin;

import com.longhum.admin.model.TAuthorityFnOperation;

public interface TAuthorityFnOperationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAuthorityFnOperation record);

    int insertSelective(TAuthorityFnOperation record);

    TAuthorityFnOperation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAuthorityFnOperation record);

    int updateByPrimaryKey(TAuthorityFnOperation record);
}