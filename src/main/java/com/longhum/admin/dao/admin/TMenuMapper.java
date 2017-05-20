package com.longhum.admin.dao.admin;

import java.util.List;

import com.longhum.admin.model.TMenu;

public interface TMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMenu record);

    int insertSelective(TMenu record);

    TMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TMenu record);

    int updateByPrimaryKey(TMenu record);

	List<TMenu> findByPid(Integer id);
}