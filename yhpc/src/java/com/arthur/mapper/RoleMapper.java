package com.arthur.mapper;

import com.arthur.pojo.Role;
import com.arthur.pojo.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    List<Role> selectByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}