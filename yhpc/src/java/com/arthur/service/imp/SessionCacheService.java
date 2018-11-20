package com.arthur.service.imp;

import net.sf.ehcache.Ehcache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class SessionCacheService<K, V> implements Cache<K, V> {
    private EhCacheManager cacheManager;
    private Cache<K,V> cache = null;
    private final static String ShiroActiveSessionCacheName = "shiro-activeSessionCache";
    @Autowired
    public SessionCacheService(EhCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public Cache<K,V> getCache() {
        try {
            if (cache == null) {
                cache = cacheManager.getCache(ShiroActiveSessionCacheName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return cache;
    }

    @Override
    public V get(K k) throws CacheException {
        return getCache().get(k);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        return getCache().put(k, v);
    }

    @Override
    public V remove(K k) throws CacheException {
        return getCache().remove(k);
    }

    @Override
    public void clear() throws CacheException {
        getCache().clear();
    }

    @Override
    public int size() {
        return getCache().size();
    }

    @Override
    public Set<K> keys() {
        return getCache().keys();
    }

    @Override
    public Collection<V> values() {
        return getCache().values();
    }
    /**
     * 获取所有Session
     * @throws Exception
     */
    public Collection<Session> allSession(){
        Set<Session> sessions = new HashSet<Session>();
        try {
            //TODO 注意事项：必须此缓存只存储Session，要不造成性能下降
            cache = getCache();
            Collection<V> values = cache.values();
            for (V v : values) {
                if (v instanceof Session && !((ValidatingSession) v).isValid()) {
                    sessions.add((Session) v);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessions;
    }
}
