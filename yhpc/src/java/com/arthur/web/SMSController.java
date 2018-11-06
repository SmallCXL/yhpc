package com.arthur.web;

import com.arthur.pojo.ResponseEntity;
import com.arthur.service.imp.SmsService;
import com.arthur.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SMSController {
    @Autowired
    UserServiceImp userServiceImp;
    @RequestMapping(value = "/getCode",method = RequestMethod.GET)
    //注意！！！在controller中返回void类型时，必须在参数列表中列出HttpServletResponse这个参数，即使你不用也要列出来，否则会报404
    @ResponseBody
    public ResponseEntity getCode(HttpServletRequest request, HttpSession session) throws Exception {
        String phoneNumber = request.getParameter("phoneNumber");
        String type = request.getParameter("type");
        if (null == phoneNumber || null == type) {
            return ResponseEntity.lackOfInformationError();
        }
        //判断是哪种情况索取验证码
        switch (type) {
            case "edit-info":
                break;
            case "forget-password":
                if (!userServiceImp.isUserExist(phoneNumber)) {
                    return ResponseEntity.userHasNotBeenRegisteredError();
                }
                break;
            case "register":
                if (userServiceImp.isUserExist(phoneNumber)) {
                    return ResponseEntity.userHasBeenRegisteredError();
                }
                break;
        }

        //保存电话号码到回话，供短信服务提取使用
        session.setAttribute("phoneNumber", phoneNumber);

        //启用发送短信服务
        SmsService.sendSms(session);
        return ResponseEntity.ok();
//        此处可以通过设置response的返回值，告知用户是否发送成功
//        response.getWriter().write("hello world");
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String handlerException(Exception e) {
        e.printStackTrace();
        return e.getMessage();
    }
}
