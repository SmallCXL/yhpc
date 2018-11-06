package com.arthur.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {
    private Cache<String,AtomicInteger> loginRetryCache;
    private int maxRetryCount;

    public RetryLimitCredentialsMatcher(CacheManager cacheManager, String cacheName, int maxRetryCount) {
        loginRetryCache = cacheManager.getCache(cacheName);
        this.maxRetryCount = maxRetryCount;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CustomTypeToken cToken = ((CustomTypeToken) token);
        String phoneNumber = (String) token.getPrincipal();
        //检查登陆类型是否为免密登陆
        if (cToken.getType().equals(LoginType.USE_VALIDATE_CODE)) {
            return true;
        }
        //记录尝试登陆次数
        AtomicInteger retryCount = loginRetryCache.get(phoneNumber);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
        }
        if (retryCount.incrementAndGet() > maxRetryCount){
            throw new ExcessiveAttemptsException("excessive attempts!");
        }
        loginRetryCache.put(phoneNumber, retryCount);
        System.out.println("phoneNumber in credential match: " + phoneNumber);
        System.out.println("retry count is: " + retryCount);
        //提交父类验证
        boolean result = super.doCredentialsMatch(token, info);
        if (result) {
            loginRetryCache.remove(phoneNumber);
        }
        return result;
    }
}
