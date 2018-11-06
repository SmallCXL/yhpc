package com.arthur.service.imp;

import com.arthur.mapper.PermissionMapper;
import com.arthur.mapper.RolePermissionMapper;
import com.arthur.pojo.*;
import com.arthur.service.intf.PermissionService;
import com.arthur.service.intf.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImp implements PermissionService {
    private PermissionMapper permissionMapper;
    private RoleService roleService;
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    public PermissionServiceImp(PermissionMapper permissionMapper, RoleService roleService,
                                RolePermissionMapper rolePermissionMapper) {
        this.permissionMapper = permissionMapper;
        this.roleService = roleService;
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public List<Permission> list() {
        PermissionExample example = new PermissionExample();
        example.setOrderByClause("id desc");
        return permissionMapper.selectByExample(example);
    }

    @Override
    public List<Permission> getUserPermission(String phoneNumber) {
        return permissionMapper.selectByPhoneNumber(phoneNumber);
    }

    @Cacheable(value = "webCache", key = "#requestURI")
    @Override
    public boolean needInterceptor(String requestURI) {
        List<Permission> list = list();
        for (Permission p : list) {
            if (p.getUrl_().equals(requestURI)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<String> listPermissionURLs(String phoneNumber) {
        Set<String> permissionURLs = new HashSet<>();
        List<Role> roles = roleService.listRoles(phoneNumber);
        List<RolePermission> rps = new ArrayList<>();
        for (Role r : roles) {
            RolePermissionExample example = new RolePermissionExample();
            example.createCriteria().andRidEqualTo(r.getId());
            List<RolePermission> list = rolePermissionMapper.selectByExample(example);
            rps.addAll(list);
        }
        for (RolePermission rp : rps) {
            Permission p = permissionMapper.getByPrimaryKey(rp.getPid());
            permissionURLs.add(p.getUrl_());
        }
        return permissionURLs;
    }
}
