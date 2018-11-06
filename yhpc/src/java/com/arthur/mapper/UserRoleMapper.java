package com.arthur.mapper;

import com.arthur.pojo.UserRole;
import com.arthur.pojo.UserRoleExample;

import java.util.List;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example);
}