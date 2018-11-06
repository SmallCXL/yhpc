package com.arthur.web;

import com.arthur.security.CustomTypeToken;
import com.arthur.service.imp.SessionCacheService;
import com.arthur.service.intf.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    @Autowired
    SessionCacheService sessionCacheService;
    private UserService userService;
    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Param("phoneNumber") String phoneNumber, @Param("password") String password,
                        @Param("validateCode") String validateCode, HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        HttpSession session = request.getSession();
        AuthenticationToken token = new CustomTypeToken(phoneNumber, password);
        //保存尝试登陆的号码
        request.setAttribute("try_number", phoneNumber);
        //验证码登陆
        if (null != validateCode) {
            //检查该手机号码是否已注册
            if (!userService.isUserExist(phoneNumber)) {
                request.setAttribute("forget_password_login_error_message", "该手机号码仍未被注册");
                return "forgetPassword";
            }
            String sendCode = ((String) session.getAttribute("sendCode"));
            //检查验证码
            if (sendCode == null || !sendCode.equals(validateCode)) {
                request.setAttribute("forget_password_login_error_message", "验证码错误或已失效");
                return "forgetPassword";
            }
            //全部通过，授予一个免密登陆的令牌
            token = new CustomTypeToken(phoneNumber);
        }
        //登陆
        try {
            subject.login(token);
            session.setAttribute("phoneNumber", phoneNumber);
            String userName = userService.getUserName(phoneNumber);
            session.setAttribute("username", userName);
            return "redirect:/userinfo";
        } catch (ExcessiveAttemptsException e) {
            request.setAttribute("login_error_message", "尝试登陆次数已超过5次，请2分钟后再尝试登陆");
        } catch (AuthenticationException e) {
            request.setAttribute("login_error_message", "用户名密码错误");
        }
        return "login";
    }
}
