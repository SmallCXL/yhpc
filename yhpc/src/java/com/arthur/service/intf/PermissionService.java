package com.arthur.service.intf;

import com.arthur.pojo.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionService {
    List<Permission> list();

    List<Permission> getUserPermission(String phoneNumber);

    boolean needInterceptor(String requestURI);

    Set<String> listPermissionURLs(String phoneNumber);
}
