package com.arthur.utils;

import com.arthur.mapper.TravelMapper;
import com.arthur.pojo.Travel;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CleanExpiredInfoJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap map = context.getJobDetail().getJobDataMap();
        TravelMapper travelMapper = ((TravelMapper) map.get("mapper"));
        Date date = new Date();
        String today = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String time = new SimpleDateFormat("hh:mm:ss").format(date);
        System.out.println("开始执行CleanExpiredInfoJob，时间："+time);
        travelMapper.doQuartzMission(today);
    }
}
