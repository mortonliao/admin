package com.longhum.admin.dao.admin;

import com.longhum.admin.model.TPageElement;

public interface TPageElementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPageElement record);

    int insertSelective(TPageElement record);

    TPageElement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPageElement record);

    int updateByPrimaryKey(TPageElement record);
}