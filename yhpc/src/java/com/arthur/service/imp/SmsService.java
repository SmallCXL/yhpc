package com.arthur.service.imp;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class SmsService {
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIwJobpiL1Lthn";
    static final String accessKeySecret = "WIj1JMvwXIj5OaAjzqf3Oi7mraXFy3";
    public static SendSmsResponse sendSms(HttpSession session) throws Exception {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        String phoneNumber = (String) session.getAttribute("phoneNumber");

        if (phoneNumber == null) {
            throw new NullPointerException("phoneNumber should not be null!");
        }
        request.setPhoneNumbers(phoneNumber);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("阳核拼车网");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_126970511");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
        String code = String.valueOf(new Random().nextFloat()).substring(2, 8);
        session.setAttribute("sendCode", code);

        final Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                session.removeAttribute("sendCode");
                timer.cancel();
            }
        },2*60*1000);//2分钟后失效

        request.setTemplateParam("{\"name\":\"Tom\", \"code\":" + code + "}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处为正式发送短信代码，可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

//      此处可以处理一下sendSmsResponse的详细内容，返回值告知短信是否发送成功。
//        return sendSmsResponse;
        System.out.println(("sendCode = " + code));
        return null;
    }
}
