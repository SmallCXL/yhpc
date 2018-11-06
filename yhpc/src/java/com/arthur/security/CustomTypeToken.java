package com.arthur.security;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CustomTypeToken extends UsernamePasswordToken {
    private LoginType type;

    public LoginType getType() {
        return type;
    }

    //免密登陆
    public CustomTypeToken(String username) {
        super(username,"",false,null);
        this.type = LoginType.USE_VALIDATE_CODE;
    }

    //加密登陆
    public CustomTypeToken(String username, String password) {
        super(username, password,false,null);
        this.type = LoginType.USE_PASSWORD;
    }
}
