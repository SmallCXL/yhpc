package com.arthur.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class JWTUtils {
    public static Algorithm algorithm;

    static {
        try {
            algorithm = Algorithm.HMAC256("Arthur");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
/**
 * @author  small
 * @create  2018 03
 * @Param  userId 用户的id，对应user数据表的主键
 * @desc
 **/
    public static String createToken(String userId) {
        try {
            HashMap headerClaims = new HashMap();
            String token = JWT.create()
                    .withIssuer("Arthur")
                    .withClaim("userId",userId)
                    .withExpiresAt(new Date())
                    .sign(algorithm);
//            System.out.println("now is =" + new Date());
            return token;
        } catch (JWTCreationException exception){
            exception.printStackTrace();
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return null;
    }

    public static DecodedJWT decodeToken(String token,String userId) throws JWTDecodeException, InvalidClaimException{
        JWTVerifier verifier = JWT
                .require(algorithm)
                .withClaim("userId", userId)
                .withIssuer("Arthur")
                .acceptExpiresAt(5)//过期时限，单位：秒
                .build();
        return verifier.verify(token);
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        System.out.println("System.currentTimeMillis() = " + now);
        String token = JWTUtils.createToken("ss");
        System.out.println("pass  = " + (System.currentTimeMillis()- now));

        DecodedJWT jwt = JWTUtils.decodeToken(token,"ss");
        //创建token和解析需要1秒钟左右,创建需要大部分时间，解析只需要50ms左右
        if (jwt != null) {
            String owner = jwt.getClaim("owner").asString();
            Date expiresAt = jwt.getExpiresAt();
            System.out.println("token = " + token);
            System.out.println("owner = " + owner);
            System.out.println("expiresAt = " + expiresAt);
        }

//        System.out.println("algorithm = " + jwt.getIssuer());
    }
}
