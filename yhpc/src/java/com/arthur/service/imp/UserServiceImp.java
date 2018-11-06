package com.arthur.service.imp;

import com.arthur.dao.TravelDAO;
import com.arthur.dao.UserDAO;
import com.arthur.mapper.UserRoleMapper;
import com.arthur.pojo.*;
import com.arthur.service.intf.UserService;
import com.arthur.utils.CheckValidateCodeUtils;
import com.arthur.utils.AjaxResult;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


//使用DAO的方法具有缓存功能，直接使用mapper的方法没有缓存功能，每次都从数据库读取
@Service
public class UserServiceImp implements UserService {
    private UserRoleMapper userRoleMapper;
    private UserDAO userDAO;
    private TravelDAO travelDAO;

    @Autowired
    public UserServiceImp(UserDAO userDAO, UserRoleMapper userRoleMapper, TravelDAO travelDAO) {
        this.userDAO = userDAO;
        this.userRoleMapper = userRoleMapper;
        this.travelDAO = travelDAO;
    }

    @Override
    public String getCredentials(String phoneNumber) {
        User u = getUser(phoneNumber);
        if (u == null) {
            return null;
        }
        return u.getPassword_();
    }

    @Override
    public User getUser(String phoneNumber) {
        return userDAO.getUser(phoneNumber);
    }

    @Override
    public String getUserName(String phoneNumber) {
        return userDAO.getUsername(phoneNumber);
    }

    @Override
    public boolean isUserExist(String phoneNumber) {
        return userDAO.getUsername(phoneNumber) != null;
    }

    //暂时没用到次方法，在管理员页面数据渲染时会用到
    @Override
    public List<User> list() {
        UserExample userExample = new UserExample();
        userExample.setOrderByClause("id desc");
        return userDAO.selectByExample(userExample);
    }

    @Override
    public void add(User user) {
        userDAO.insertSelective(user);
    }

    @CacheEvict(value = "webCache", key = "'user:'+#phoneNumber")
    @Override
    public void delete(String phoneNumber) {
        userDAO.deleteByPhoneNumber(phoneNumber);
    }

    //创建新用户
    @Override
    @Transactional
    public AjaxResult createUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String phoneNumber = request.getParameter("phoneNumber");
        String inputCode = request.getParameter("inputCode");
        String sendCode = (String) session.getAttribute("sendCode");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        //检测表单数据是否成功获取
        if (null == phoneNumber || null == inputCode || null == userName || null == password || null == sex) {
            return AjaxResult.MissingInformation;
        }
        //检测账户是否已注册
        if (isUserExist(phoneNumber)) {
            return AjaxResult.UserHasBeenRegistered;
        }
        //检测验证码是否正确
        if (null==sendCode || !sendCode.equals(inputCode)) {
            return AjaxResult.IdentifyingCodeError;
        }
        User user = new User();
        user.setPhone_(phoneNumber);
        user.setName_(userName);

        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        user.setSalt_(salt);

        String encodedPassword = new SimpleHash("md5", password, salt, 2).toString();
        user.setPassword_(encodedPassword);

        user.setSex_(sex);

        String today = new SimpleDateFormat("MMdd").format(new Date());
        StringBuilder validateCode = new StringBuilder();
        validateCode.append(today).append("0").append(today.substring(0,2)).append("0");
        user.setValidate_code_(validateCode.toString());

        add(user);
        //给用户添加角色"user"
        UserRole userRole = new UserRole();
        userRole.setRid(Role.USER);
        userRole.setUid(user.getId());
        userRoleMapper.insert(userRole);

        return AjaxResult.OK;
    }

    //更新用户信息
    @Override
    @Transactional
    public AjaxResult updateUserInfo(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String inputCode = request.getParameter("inputCode");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        String sendCode = ((String) session.getAttribute("sendCode"));
        //检测表单数据是否成功获取
        if (null == inputCode|| null == userName || null == password) {
            return AjaxResult.MissingInformation;
        }
        if (null == sendCode || !sendCode.equals(inputCode)) {
            return AjaxResult.IdentifyingCodeError;
        }

        String phoneNumber = ((String) session.getAttribute("phoneNumber"));

        User user = userDAO.getUser(phoneNumber);
        String code;

//        //检查原来的密码是否正确
//        String passwordInDB = user.getPassword_();
//        String salt = user.getSalt_();
//        String encodedPassword = new SimpleHash("md5", oldPassword, salt, 2).toString();
//
//        if (!passwordInDB.equals(encodedPassword)) {
//            return AjaxResult.PasswordIncorrect;
//        }

        //尝试修改 限制验证码，并返回修改后的验证码，若出错则说明修改次数已达上限
        try {
            code = CheckValidateCodeUtils.tryToEditInfo(user.getValidate_code_());
        } catch (IllegalStateException e) {
            return AjaxResult.ExcessiveEditTimes;
        }

        updateValidateCode(user, code);
        session.setAttribute("is_info_edited", 1);

        user.setValidate_code_(code);
        user.setName_(userName);

        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        user.setSalt_(salt);

        String encodedPassword = new SimpleHash("md5", password, salt, 2).toString();
        user.setPassword_(encodedPassword);

        userDAO.update(user);
        return AjaxResult.OK;
    }

    @Override
    public void updateValidateCode(User user, String validateCode) {
        userDAO.updateValidateCode(user,validateCode);
    }

    //查询用户的详细信息，包括已发布的拼车信息，并将已使用的次数，是否修改过用户信息 保存在session中
    @Override
    public void queryUserEntireInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String phoneNumber = ((String) session.getAttribute("phoneNumber"));
        User user = getUser(phoneNumber);
        List<Travel> list = travelDAO.selectTravelListOfUser(user);
        user.setTravelList(list);
        session.setAttribute("userinfo", user);
//        session.setAttribute("username",user.getName_());
        session.setAttribute("publish_frequency", CheckValidateCodeUtils.getPublishedFrequency(user.getValidate_code_()));
        session.setAttribute("is_info_edited", CheckValidateCodeUtils.isUserInfoModified(user.getValidate_code_()));
    }

}
