package com.arthur.service.intf;


import com.arthur.utils.AjaxResult;

import javax.servlet.http.HttpServletRequest;

public interface TravelService {
    AjaxResult publishTravelInfo(HttpServletRequest request);

    AjaxResult deleteTravelInfo(HttpServletRequest request);
}
