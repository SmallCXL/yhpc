package com.arthur.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class CheckValidateCodeUtils {
    /*
     * @author  
     * @create  2018/10/8 下午2:01
     * @Param  validateCode 例如10074101 前5位表示，10月07日，发布了4条，后3位表示10月已进行用户信息修改
     * @Return
     * @desc
     */
    //获取今天已发布的次数
    public static int getPublishedFrequency(String validateCode) throws IllegalArgumentException{
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String today = sdf.format(new Date());
        if (validateCode.substring(0, 4).equals(today)) {
            String frequency = validateCode.substring(4, 5);
            return Integer.parseInt(frequency);
        }
        return 0;
    }

    public static int isUserInfoModified(String validateCode) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String thisMonth = sdf.format(new Date());
        if (validateCode.substring(5, 7).equals(thisMonth) && validateCode.substring(7, 8).equals("1")) {
            return 1;
        } else return 0;
    }

    public static String tryToPublish(String validateCode) throws IllegalStateException{
        int frequency = getPublishedFrequency(validateCode);
        StringBuilder sb = new StringBuilder(validateCode);
        if (frequency > 4) {
            throw new IllegalStateException("发布次数已达上限");
        } else if (frequency == 0) {
            String today = new SimpleDateFormat("MMdd").format(new Date());
            return sb.replace(0, 5, today + "1").toString();
        } else {
            return sb.replace(4, 5, String.valueOf(frequency + 1)).toString();
        }
    }

    public static String tryToEditInfo(String validateCode) throws IllegalStateException{
        StringBuilder sb = new StringBuilder(validateCode);
        if (isUserInfoModified(validateCode) > 0) {
            throw new IllegalStateException("本月已修改");
        } else {
            sb.replace(5, 8, new SimpleDateFormat("MM").format(new Date())).append("1");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        String code = "10063101";
        try {
            System.out.println(tryToEditInfo(code));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis());
    }
}
