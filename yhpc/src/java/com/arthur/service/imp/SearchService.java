package com.arthur.service.imp;

import com.arthur.mapper.TravelMapper;
import com.arthur.pojo.Travel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class SearchService {
    private static final int ROWS_PER_PAGE = 10;
    private TravelMapper travelMapper;
    @Autowired
    public SearchService(TravelMapper travelMapper){
        this.travelMapper = travelMapper;
    }
    private int getResultRows(String departure, String arrival,String type) {
        return travelMapper.countResult(departure, arrival, type);
    }

    public List<Travel> search(String departure, String arrival, int offset, int limit, String type) {
        return travelMapper.queryWithKey(departure, arrival, offset, limit, type);
    }
    //此方法用于在原有搜索结果之上，搜索下一页内容，搜索完后不需要进行表单数据（出发地、目的地、类型）的保存和更改
    //但是需要对当前页码进行修改并保存到会话中，最后将搜索的数据list存入会话
    public void searchNextPage(HttpServletRequest request) {
        HttpSession session = request.getSession();

        //获取会话中的表单数据
        String departure = ((String) session.getAttribute("departure"));
        String arrival = ((String) session.getAttribute("arrival"));
        String type = ((String) session.getAttribute("type"));

        //检查是否已经存有本次搜索结果的总行数
        int totalRows;
        Object rows = session.getAttribute("totalRows");
        //存了，获取
        if (rows != null) {
            totalRows = ((int) rows);
        } else{
            totalRows = getResultRows(null, null, type);
            session.setAttribute("totalRows",totalRows);
        }


        int currentPage;
        try {
            currentPage = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            currentPage = 1;
        }
        session.setAttribute("currentPage", currentPage);
        //计算总页数
        int totalPages = totalRows % ROWS_PER_PAGE == 0 ? totalRows / ROWS_PER_PAGE : totalRows / ROWS_PER_PAGE + 1;
        session.setAttribute("totalPages", totalPages);

        int offset = (currentPage - 1) * ROWS_PER_PAGE;

        List<Travel> list = search(departure, arrival, offset, ROWS_PER_PAGE, type);

        session.setAttribute("travel_info_list",list);
    }

    //此方法需要将表单数据（出发地、目的地、类型）保存到会话中，但是不需要对当前页码进行操作，定位在第一页即可
    public void searchDepartureAndArrival(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String departure = request.getParameter("departure");
        String arrival = request.getParameter("arrival");
        String type = request.getParameter("type");

        if (departure != null) {
            departure = departure.trim();
        }
        if (arrival != null) {
            arrival = arrival.trim();
        }

        int searchResultRows = getResultRows(departure, arrival, type);
        int totalPages = searchResultRows % ROWS_PER_PAGE == 0 ? searchResultRows / ROWS_PER_PAGE : searchResultRows / ROWS_PER_PAGE + 1;

        session.setAttribute("currentPage",1);//每次提交搜索后，定位在第一页
        session.setAttribute("totalRows",searchResultRows);
        session.setAttribute("totalPages",totalPages);

        List<Travel> list = search(departure, arrival, 0, ROWS_PER_PAGE, type);
        session.setAttribute("travel_info_list", list);

        //若搜索结果为空，则将表单信息清空，防止返回时自动填写，造成BUG
        if (list.size() == 0) {
            session.setAttribute("departure", null);
            session.setAttribute("arrival", null);
            session.setAttribute("type",null);
        } else {
            session.setAttribute("departure", departure);
            session.setAttribute("arrival", arrival);
            session.setAttribute("type", type);
        }
    }

}
