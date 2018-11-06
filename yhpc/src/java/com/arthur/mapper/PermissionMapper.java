package com.arthur.mapper;

import com.arthur.pojo.Permission;
import com.arthur.pojo.PermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    List<Permission> selectByPhoneNumber(String phoneNumber);

    Permission getByPrimaryKey(@Param("id") long id);
}