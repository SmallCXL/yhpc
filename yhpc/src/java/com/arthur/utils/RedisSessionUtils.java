package com.arthur.utils;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisSessionUtils {
    private RedisTemplate<String, Session> redisTemplate;
    @Autowired
    public RedisSessionUtils(RedisTemplate<String, Session> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    private static final String KEY = "sharedSessionMap";

    public void hAdd(String sessionId, Session session, long expire) {
        redisTemplate.boundHashOps(KEY).put(sessionId, session);
        redisTemplate.expire(KEY, expire, TimeUnit.MILLISECONDS);
    }

    public void hDelete(String sessionId){
        redisTemplate.boundHashOps(KEY).delete(sessionId);
    }

    public Session hGet(String sessionId){
        return (Session) redisTemplate.boundHashOps(KEY).get(sessionId);
    }

    public List<Session> hMGet(){
        List<Session> list = new ArrayList<>();

        List<Object> values = redisTemplate.boundHashOps(KEY).values();
        for (Object object : values) {
            list.add((Session) object);
        }
        return list;
    }
}
