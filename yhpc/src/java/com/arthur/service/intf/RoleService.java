package com.arthur.service.intf;

import com.arthur.pojo.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<String> getRoleNames(String phoneNumber);

    List<Role> listRoles(String phoneNumber);


}
