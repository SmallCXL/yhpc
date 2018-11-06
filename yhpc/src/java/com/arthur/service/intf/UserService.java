package com.arthur.service.intf;

import com.arthur.pojo.User;
import com.arthur.utils.AjaxResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    String getCredentials(String phoneNumber);

    User getUser(String phoneNumber);

    String getUserName(String phoneNumber);

    boolean isUserExist(String phoneNumber);

    AjaxResult createUser(HttpServletRequest request);

    List<User> list();

    void add(User user);

    void delete(String phoneNumber);

    AjaxResult updateUserInfo(HttpServletRequest request);

    void updateValidateCode(User user, String validateCode);

    void queryUserEntireInfo(HttpServletRequest request);
}
