package com.kireev.springboot.demo.dao;


import com.kireev.springboot.demo.models.Role;

import java.util.Set;

public interface RoleDao {
    public Set<Role> getAll();

    Role getRole(long id);

    Role findRoleByName(String roleName);
}
