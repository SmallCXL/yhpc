package com.arthur.filter;

import com.arthur.service.intf.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Set;
@Component
public class URLPathMatchingFilter extends PathMatchingFilter {

    private PermissionService permissionService;
    @Autowired
    public URLPathMatchingFilter(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        String requestURI = getPathWithinApplication(request);
        Subject subject = SecurityUtils.getSubject();
        //如果没有登录就重定向至登录界面
        if (!subject.isAuthenticated()) {
            WebUtils.issueRedirect(request, response, "/login");
            return false;
        }
        boolean needInterceptor = permissionService.needInterceptor(requestURI);
        //当前路径不需要权限可以访问
        if (!needInterceptor) {
            return true;
        }
        //需要权限验证
        else {
            String phoneNumber = subject.getPrincipal().toString();
            Set<String> permissionURLs = permissionService.listPermissionURLs(phoneNumber);
            for (String url : permissionURLs) {
                //用户具有当前路径的访问权限
                if (url.equals(requestURI)) {
                    return true;
                }
            }
            //遍历完后发现用户没有访问当前路径的权限
            UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径: " + requestURI + " 的权限");
            subject.getSession().setAttribute("ex", ex);
            WebUtils.issueRedirect(request, response, "/unauthorized");
            return false;
        }
    }
}
