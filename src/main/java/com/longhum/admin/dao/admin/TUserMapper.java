package com.longhum.admin.dao.admin;

import com.longhum.admin.model.TUser;

public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

	TUser findByLoginName(String currentLoginName);
	
}