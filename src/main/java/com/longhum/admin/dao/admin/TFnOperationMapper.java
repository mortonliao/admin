package com.longhum.admin.dao.admin;

import com.longhum.admin.model.TFnOperation;

public interface TFnOperationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TFnOperation record);

    int insertSelective(TFnOperation record);

    TFnOperation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TFnOperation record);

    int updateByPrimaryKey(TFnOperation record);
}