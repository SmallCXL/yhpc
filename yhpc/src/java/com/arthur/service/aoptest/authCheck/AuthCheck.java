package com.arthur.service.aoptest.authCheck;

import com.arthur.utils.JWTUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthCheck {
    @Pointcut("@annotation(com.arthur.service.aoptest.annotation.AuthCheck)")
    public void pointCut() {
    }
    @Around("pointCut()")
    public Object checkAuth(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        System.out.println("token from browser = " + token);

        if (token != null && !token.equals("") && !token.equals("null")) {
            System.out.println("now checking token...");
            String userId = request.getHeader("userId");
            JWTUtils.decodeToken(token,userId);
            return pjp.proceed();
//            if (token.equals("123456")) {
//                System.out.println("token is correct!");
//                return pjp.proceed();
//            } else {
//                System.out.println("token is incorrect!");
//                throw new NullPointerException("未授权！");
//            }
        } else {
            String user = request.getParameter("user");
            String password = request.getParameter("password");
            System.out.println("now checking password...");
            if (user.equals("small") && password.equals("123456")) {
                System.out.println("password correct!");
                return pjp.proceed();
            } else {
                System.out.println("password is incorrect!");
                throw new NullPointerException("用户名密码不正确");
            }
        }
    }
    //将token存在Cookie中，不能防御CSRF攻击，同一个浏览器发起一个没有验证的请求，同样可以通过验证，不安全
//    @Around("pointCut()")
//    public Object checkAuth(ProceedingJoinPoint pjp) throws Throwable {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        Cookie[] cookies = request.getCookies();
//        String token = null;
//        if (cookies != null && cookies.length > 0) {
//            for (Cookie c :
//                    cookies) {
//                if (c.getName().equals("token")) {
//                    token = c.getValue();
//                }
//            }
//        }
//        if (token != null && !token.equals("")) {
//            if (token.equals("123456")) {
//                return pjp.proceed();
//            } else {
//                throw new NullPointerException("token验证失败");
//            }
//        }
//        String user = request.getParameter("user");
//        String password = request.getParameter("password");
//        if (user.equals("small") && password.equals("123456")) {
//            return pjp.proceed();
//        }
//        throw new NullPointerException("用户名密码错误！");
//    }
}