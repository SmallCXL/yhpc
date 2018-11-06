package com.arthur.dao;

import com.arthur.mapper.UserMapper;
import com.arthur.pojo.Travel;
import com.arthur.pojo.User;
import com.arthur.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

//本类其实可以省略，直接调用mapper即可，但是为了对服务数据进行缓存，增加此类。
// 服务的类不能调用自身中方法的缓存数据（缓存基于aop动态代理，本类不会调用代理，故缓存本类的方法结果无效，需要缓存在另一个类）
@Repository
public class UserDAO {
    private UserMapper userMapper;
    @Autowired
    public UserDAO(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //cache user:123456
    @Cacheable(value = "webCache", key = "'user:'+#phoneNumber")
    public User getUser(String phoneNumber) {
        System.out.println("loading user data from db...");
        return userMapper.getUser(phoneNumber);
    }

    public long insert(User user) {
        return userMapper.insert(user);
    }

    public String getUsername(String phoneNumber) {
        return userMapper.getUsername(phoneNumber);
    }

    public long insertSelective(User user) {
        return userMapper.insertSelective(user);
    }

    public List<User> selectByExample(UserExample example) {
        return userMapper.selectByExample(example);
    }

    @CachePut(value = "webCache", key = "'user:'+#user.phone_")
    public User update(User user) {
        userMapper.update(user);
        return user;
    }

    @CacheEvict(value = "webCache", key = "'user:'+#phoneNumber")
    public void deleteByPhoneNumber(String phoneNumber) {
        userMapper.deleteByPhoneNumber(phoneNumber);
    }

    public List<Travel> selectTravelInfo(Long uid, int offset, int limit) {
        return userMapper.selectTravelInfo(uid, offset, limit);
    }

    //更新发布和修改次数的限制码，每次都会执行方法体，同时更新缓存：user:xxxx
    @CachePut(value = "webCache", key = "'user:'+#user.phone_")
    public User updateValidateCode(User user, String validateCode) {
        userMapper.updateValidateCode(user, validateCode);
        user.setValidate_code_(validateCode);
        return user;
    }
}
