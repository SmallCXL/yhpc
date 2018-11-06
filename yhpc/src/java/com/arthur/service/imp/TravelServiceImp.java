package com.arthur.service.imp;

import com.arthur.dao.TravelDAO;
import com.arthur.dao.UserDAO;
import com.arthur.pojo.Travel;
import com.arthur.pojo.User;
import com.arthur.service.intf.TravelService;
import com.arthur.service.intf.UserService;
import com.arthur.utils.CheckValidateCodeUtils;
import com.arthur.utils.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//使用DAO的方法具有缓存功能，直接使用mapper的方法没有缓存功能，每次都从数据库读取
@Service
public class TravelServiceImp implements TravelService {
    private UserService userService;
    private TravelDAO travelDAO;
    private UserDAO userDAO;

    @Autowired
    public TravelServiceImp(UserService userService, TravelDAO travelDAO, UserDAO userDAO) {
        this.userDAO = userDAO;
        this.userService = userService;
        this.travelDAO = travelDAO;
    }

    @Override
    @Transactional
    public AjaxResult publishTravelInfo(HttpServletRequest request) throws AuthenticationException {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            throw new AuthenticationException("no authority");
        }
        String principal = ((String) subject.getPrincipal());
        User user = userService.getUser(principal);

        //检查今日发布次数
        String code;
        try {
            code = CheckValidateCodeUtils.tryToPublish(user.getValidate_code_());
        } catch (IllegalStateException e) {
            return AjaxResult.ExcessivePublishTimes;
        }
        //保存已发布次数到DB
        userService.updateValidateCode(user,code);
        //保存已发布信息到会话
        request.getSession().setAttribute("publish_frequency", CheckValidateCodeUtils.getPublishedFrequency(code));

        String departure = request.getParameter("departure");
        String arrival = request.getParameter("arrival");
        String travel_time = request.getParameter("travel_time");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Timestamp t_time ;
        try {
            t_time = new Timestamp(format.parse(travel_time).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return AjaxResult.MissingInformation;
        }

        String type = request.getParameter("type");
        String addition = request.getParameter("addition");
        String publish_time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());

        Long uid = user.getId();

        Travel travel = new Travel();
        travel.setDeparture_(departure);
        travel.setAddition_(addition);
        travel.setArrival_(arrival);
        travel.setTravel_time_(t_time);
        travel.setType_(type);
        travel.setUid(uid);
        travel.setPublish_time_(publish_time);

        travelDAO.insert(user,travel);
        return AjaxResult.OK;
    }


    @Override
    public AjaxResult deleteTravelInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String phoneNumber = ((String) session.getAttribute("phoneNumber"));
        long id = Long.parseLong(request.getParameter("id"));
        User user = userDAO.getUser(phoneNumber);
        travelDAO.deleteById(user, id);
        return AjaxResult.OK;
    }

}
