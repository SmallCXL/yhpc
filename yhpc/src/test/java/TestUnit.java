
import com.arthur.mapper.PermissionMapper;
import com.arthur.mapper.TravelMapper;
import com.arthur.mapper.UserMapper;
import com.arthur.dao.UserDAO;
import com.arthur.pojo.Travel;
import com.arthur.pojo.TravelExample;
import com.arthur.pojo.User;
import com.arthur.service.imp.SearchService;
import com.arthur.service.imp.SessionCacheService;
import com.arthur.service.imp.UserServiceImp;
import com.arthur.service.intf.PermissionService;
import com.arthur.service.intf.TravelService;
import com.arthur.utils.CleanExpiredInfoJob;
import com.arthur.utils.RedisSessionUtils;
import com.arthur.utils.RedisUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:resources/applicationContext.xml","classpath:resources/spring-web.xml"})
public class TestUnit {
    //    private static final Logger logger = LoggerFactory.getLogger(TestUnit.class);
    @Autowired
    TravelMapper travelMapper;
    @Autowired
    RedisSessionUtils redisSessionUtils;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserServiceImp uService;
    @Autowired
    SearchService searchService;
    @Autowired
    TravelService travelService;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    PermissionService permissionService;
    @Autowired
    UserDAO userDAO;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    SessionCacheService sessionCacheService;

    @Test
    public void testDB() {
//        List<Travel> list = searchService.search(" 阳江 "," 容县  ",0,5,null);
//        System.out.println("list.size() = " + list.size());
//        for (Travel t :
//                list) {
//            System.out.println("-------------------------");
//            System.out.println(t.getUser().getName_());
//            System.out.println(t.getUser().getPhone_());
//            System.out.println(t.getUser().getSex_().equals("1")?"男":"女");
//            System.out.println("");
//        }
//        User user = new User();
//        user.setId(11L);
//        userDAO.updateValidateCode(user,"10080101");

    }
    @Test
    public void testEncodingPassword() {
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        System.out.println("salt = " + salt);
        String encodedPassword = new SimpleHash("md5", "xiaolong", salt, 2).toString();
        System.out.println("encodedPassword = " + encodedPassword);


    }
    @Test
    public void testLogin() {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("13541095940", "cxl44956520");
        subject.login(token);
    }
    @Test
    public void testUserService() {
//        User user = uService.getUser("18023891508");
//        Long uid = user.getId();
//        List<Travel> list = uService.queryUserEntireInfo(uid);
        User user = new User();
        user.setName_("chenxiaolong");
        user.setSex_("1");
        user.setPassword_("cxl123456");
        user.setSalt_("123456");
        user.setPhone_("18023891509");
        long uid = userMapper.insert(user);
        System.out.println("uid = " + user.getId());
    }
    @Test
    public void testTravelService() {
//        int i = travelService.deleteTravelInfo(17);
//        System.out.println(i);
//        List<Travel> list = travelMapper.queryWithKey(null, null, 0, 5, null);
//        for (Travel t :
//                list) {
//            System.out.println(t.getId());
//        }
//        Travel travel = new Travel();
//        travel.setUid(9L);
//        travel.setDeparture_("阳江核电基地");
//        travel.setArrival_("容县城南客运站");
//        travel.setType_("2");
//        travel.setAddition_("希望车上有吃喝，有电视看");
//        travel.setTravel_time_("2018-10-3 14:30");
//        travelService.publishTravelInfo(travel);
        TravelExample example = new TravelExample();
        example.createCriteria().andUidEqualTo(11L);
        example.setOrderByClause("id desc");
        List<Travel> list = travelMapper.selectByExample(example);
        for (Travel t:
             list) {
            System.out.println("---------------------------");
            System.out.println(t.getDeparture_());
            System.out.println(t.getArrival_());
            System.out.println(t.getTravel_time_());
            System.out.println(t.getAddition_());
        }
    }

    @Test
    public void testEnum() {
        System.out.println(permissionMapper.getByPrimaryKey(1).getUrl_());
    }

    @Test
    public void testString() {
//        System.out.println("2018-10-09 10:30".substring(5,10));
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        System.out.println(format.format(new Date()));
    }
    @Test
    public void testCache() {
        System.out.println(redisSessionUtils.hMGet().get(0).getId());
    }

    @Test
    public void testQuartz() throws SchedulerException, InterruptedException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        CronTrigger trigger = newTrigger().withIdentity("mainJob", "G1")
                .withSchedule(cronSchedule("0/5 * * * * ?")).build();
        JobDetail job = newJob(CleanExpiredInfoJob.class).withIdentity("mainJob", "G1").build();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
//        Thread.sleep(20000);
//        scheduler.shutdown();
    }

    @Test
    public void testDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM-dd hh:mm");
        String s = "2018 10-19 00:00";
        String s2 = "2018 10-20 00:00";
        Date date = sdf.parse(s);
        Date date2 = sdf.parse(s2);
        Timestamp ts = new Timestamp(date.getTime());
        System.out.println(ts);
//        System.out.println(date.getTime());
    }
    @Test
    public void testRedis() {
//        redisUtils.set("chenxiaolong","small");
//        System.out.println(redisUtils.get("chenxiaolong"));
//        long incr = redisUtils.incr("number", 1);
//        System.out.println(incr);
//        incr = redisUtils.incr("number", 2);
//        System.out.println(incr);
//        Map<String,Object> map=new HashMap<>();
//        map.put("name", "meepo");
//        map.put("pwd", "password");
//        redisUtils.hmset("user", map);
//        System.out.println(redisUtils.hGet("user","name"));
    }
}
