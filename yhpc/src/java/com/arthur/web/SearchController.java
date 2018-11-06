package com.arthur.web;

import com.arthur.pojo.ResponseEntity;
import com.arthur.service.imp.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SearchController {

    private SearchService searchService;
    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity searchDepartureAndArrival(HttpServletRequest request) {
        searchService.searchDepartureAndArrival(request);
        return ResponseEntity.ok();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchNextPage(HttpServletRequest request) {
        searchService.searchNextPage(request);
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(HttpServletRequest request,HttpSession session) {
        session.setAttribute("departure", null);
        session.setAttribute("arrival", null);
        session.setAttribute("type", null);
        searchService.searchDepartureAndArrival(request);
        return "index";
    }
}
