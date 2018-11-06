package com.arthur.mapper;

import com.arthur.pojo.RolePermission;
import com.arthur.pojo.RolePermissionExample;

import java.util.List;

public interface RolePermissionMapper {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    List<RolePermission> selectByExample(RolePermissionExample example);
}