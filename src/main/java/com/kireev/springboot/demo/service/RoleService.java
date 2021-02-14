package com.kireev.springboot.demo.service;


import com.kireev.springboot.demo.models.Role;

import java.util.Set;

public interface RoleService {

    public Set<Role> getAll();

    Role getRole(long id);

    Role findRoleByName(String role);
}
