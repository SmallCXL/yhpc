package com.arthur.pojo;

import java.util.HashMap;
import java.util.Map;

public class ResponseEntity {
    private final String message;
    private final int code;
    private final Map<String,Object> data = new HashMap<>();

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    private ResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseEntity setDataValue(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public static ResponseEntity ok() {
        return new ResponseEntity(200, "OK");
    }

    public static ResponseEntity notFound() {
        return new ResponseEntity(404, "Not Found");
    }

    public static ResponseEntity badRequest() {
        return new ResponseEntity(400, "请求出错，请稍后再试");
    }

    public static ResponseEntity forbidden() {
        return new ResponseEntity(403, "Forbidden");
    }

    public static ResponseEntity unauthorized() {
        return new ResponseEntity(401, "Unauthorized");
    }

    public static ResponseEntity serverInternalError() {
        return new ResponseEntity(500, "Server Internal Error");
    }

    public static ResponseEntity identifyingCodeError() {
        return new ResponseEntity(1001, "验证码错误或已失效");
    }
    
    public static ResponseEntity userHasBeenRegisteredError() {
        return new ResponseEntity(1002, "该手机号码已被注册");
    }
    public static ResponseEntity lackOfInformationError() {
        return new ResponseEntity(1003, "缺少完整的信息");
    }
    public static ResponseEntity deleteInfoError() {
        return new ResponseEntity(1004, "删除信息失败");
    }
    public static ResponseEntity excessivePublishTimesError() {
        return new ResponseEntity(1005, "今日发布次数已达上限");
    }
    public static ResponseEntity excessiveEditTimesError() {
        return new ResponseEntity(1006, "本月修改信息次数已达上限");
    }
    public static ResponseEntity passwordIncorrect() {
        return new ResponseEntity(1007, "密码错误");
    }
    public static ResponseEntity userHasNotBeenRegisteredError() {
        return new ResponseEntity(1008, "该用户仍未注册");
    }


}
