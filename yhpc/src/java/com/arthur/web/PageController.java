package com.arthur.web;

import com.arthur.pojo.ResponseEntity;
import com.arthur.pojo.Travel;
import com.arthur.service.imp.SearchService;
import com.arthur.service.intf.TravelService;
import com.arthur.service.intf.UserService;
import com.arthur.utils.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PageController {
    private UserService userService;
    private TravelService travelService;
    private SearchService searchService;

    @Autowired
    public PageController(UserService userService,TravelService travelService,SearchService searchService) {
        this.userService = userService;
        this.travelService = travelService;
        this.searchService = searchService;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpSession session) {
        List<Travel> list = (List<Travel>) session.getAttribute("travel_info_list");
        if (list == null) {
            session.setAttribute("departure", null);
            session.setAttribute("arrival", null);
            session.setAttribute("type", null);
            searchService.searchDepartureAndArrival(request);
        }
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping("/publish")
    public String publish() {
        return "publish";
    }

    @RequestMapping(value = "/publishSubmit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity publishSubmit(HttpServletRequest request) {
        AjaxResult ajaxResult = travelService.publishTravelInfo(request);
        switch (ajaxResult) {
            case ExcessivePublishTimes:
                return ResponseEntity.excessivePublishTimesError();
            case OK:
                return ResponseEntity.ok();
            case MissingInformation:
            default:
                return ResponseEntity.badRequest();
        }
    }

    @RequestMapping("/userinfo")
    public String userInfo(HttpServletRequest request) {
        userService.queryUserEntireInfo(request);
        return "userinfo";
    }

    @RequestMapping("/editinfo")
    public String editInfo() {
        return "editinfo";
    }

    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity editSubmit(HttpServletRequest request) {
        AjaxResult ajaxResult = userService.updateUserInfo(request);
        switch (ajaxResult) {
            case IdentifyingCodeError:
                return ResponseEntity.identifyingCodeError();
            case MissingInformation:
                return ResponseEntity.lackOfInformationError();
            case ExcessiveEditTimes:
                return ResponseEntity.excessiveEditTimesError();
            case PasswordIncorrect:
                return ResponseEntity.passwordIncorrect();
            case OK:
        }
        return ResponseEntity.ok();
    }

    @RequestMapping(value = "/deleteTravel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity deleteTravelInfo(HttpServletRequest request) {
        AjaxResult ajaxResult = travelService.deleteTravelInfo(request);
        switch (ajaxResult) {
            case DeleteInfoError:
                return ResponseEntity.deleteInfoError();
            case OK:
        }
        return ResponseEntity.ok();
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/registerSubmit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity registerSubmit(HttpServletRequest request) {
        AjaxResult createAjaxResult = userService.createUser(request);
        switch (createAjaxResult) {
            case UserHasBeenRegistered:
                return ResponseEntity.userHasBeenRegisteredError();
            case IdentifyingCodeError:
                return ResponseEntity.identifyingCodeError();
            case MissingInformation:
                return ResponseEntity.lackOfInformationError();
            case OK:
        }
        return ResponseEntity.ok();
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            session.removeAttribute("subject");
            subject.logout();
        }
        return "logout";
    }


    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    @RequestMapping("/forgetPassword")
    public String forgetPassword() {
        return "forgetPassword";
    }

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String test(HttpServletRequest request) {
        String d = request.getParameter("user");
        String a = request.getParameter("password");
        String addition = request.getParameter("phoneNumber");

        System.out.println(d);
        System.out.println(a);
        System.out.println(addition);
        return "test";
    }

    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "loginFailure";
    }

//    @RequestMapping("/admin")
//    public String adminTest() {
//        System.out.println("enter admin url");
//        return "adminInfo";
//    }
}
