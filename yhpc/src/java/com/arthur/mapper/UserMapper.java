package com.arthur.mapper;

import com.arthur.pojo.Travel;
import com.arthur.pojo.User;
import com.arthur.pojo.UserExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface UserMapper {

    User getUser(@Param("phoneNumber") String phoneNumber);

    long insert(User record);

    long insertSelective(User record);

    List<User> selectByExample(UserExample example);

    void update(User user);

    void deleteByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    List<Travel> selectTravelInfo(@Param("uid") Long uid, @Param("offset") int offset, @Param("limit") int limit);

    void updateValidateCode(@Param("user") User user, @Param("validate_code_") String validateCode);

    String getUsername(@Param("phoneNumber") String phoneNumber);
}