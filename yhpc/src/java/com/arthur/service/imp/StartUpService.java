package com.arthur.service.imp;

import com.arthur.mapper.TravelMapper;
import com.arthur.pojo.Travel;
import com.arthur.utils.CleanExpiredInfoJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Component
public class StartUpService implements InitializingBean, ServletContextAware, ServletContextListener{

    @Resource
    private
    TravelMapper travelMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public void setServletContext(ServletContext servletContext) {

        //开启定时任务，每天凌晨4点执行清理过期信息任务
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            CronTrigger trigger = newTrigger().withIdentity("clean_expired_info_mission", "G1")
                    .withSchedule(cronSchedule("0 0 4 * * ?")).build();
            JobDataMap map = new JobDataMap();
            map.put("mapper", travelMapper);
            JobDetail job = newJob(CleanExpiredInfoJob.class)
                    .withIdentity("clean_expired_info_mission", "G1")
                    .setJobData(map).build();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            Thread.sleep(1000);
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.shutdown();
        } catch (InterruptedException | SchedulerException e) {
            e.printStackTrace();
        }
    }
}
