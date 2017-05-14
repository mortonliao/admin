package com.longhum.admin.dao.admin;

import org.apache.ibatis.annotations.Mapper;

import com.longhum.admin.model.User;
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}