package com.arthur.dao;

import com.arthur.utils.RedisSessionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;

@Component
public class BackUpAbstractSessionDAO extends AbstractSessionDAO{
    private RedisSessionUtils redisSessionUtils;
    private long expire;//毫秒
    private static final String KEY = "shareSessionMap";

    @Autowired
    public BackUpAbstractSessionDAO(RedisSessionUtils redisSessionUtils) {
        this.redisSessionUtils = redisSessionUtils;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    @Override
    protected Serializable doCreate(Session session) {

        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        redisSessionUtils.hAdd(sessionId.toString(), session, expire);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return redisSessionUtils.hGet(sessionId.toString());
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
            return;
        }
        redisSessionUtils.hAdd(session.getId().toString(), session, expire);

    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            return;
        }
        redisSessionUtils.hDelete(session.getId().toString());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return redisSessionUtils.hMGet();
    }
}
