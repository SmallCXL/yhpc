package com.arthur.service.imp;

import com.arthur.mapper.RoleMapper;
import com.arthur.pojo.Role;
import com.arthur.service.intf.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {
    private RoleMapper roleMapper;

    @Autowired
    public RoleServiceImp(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public Set<String> getRoleNames(String phoneNumber) {
        Set<String> roleNames = new HashSet<>();
        List<Role> list = roleMapper.selectByPhoneNumber(phoneNumber);
        for (Role r : list) {
            roleNames.add(r.getName_());
        }
        return roleNames;
    }

    @Override
    public List<Role> listRoles(String phoneNumber) {
        return roleMapper.selectByPhoneNumber(phoneNumber);
    }
}
