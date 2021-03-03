package com.kireev.springboot.demo.controllers;

import com.kireev.springboot.demo.models.Role;
import com.kireev.springboot.demo.models.User;
import com.kireev.springboot.demo.service.RoleService;
import com.kireev.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class RestUserController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public RestUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "allUsers", headers="Accept=application/json")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("admin")
    public @ResponseBody List<User> addUser(@RequestBody User user) {
        user.setRoles(newSet(user.getRoles()));
        userService.editUser(user);
        return userService.getAllUsers();
    }

    @PutMapping("admin")
    public @ResponseBody List<User> editUser(@RequestBody User user) {
        user.setRoles(newSet(user.getRoles()));
        userService.editUser(user);
        return userService.getAllUsers();
    }

    @GetMapping("admin/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUser(id);
    }

    @GetMapping(value = "roles", headers="Accept=application/json")
    public Set<Role> allRoles() {
        return roleService.getAll();
    }

    @DeleteMapping("admin/{id}")
    public @ResponseBody List<User> deleteUserById(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return userService.getAllUsers();
    }

    @GetMapping("/userInfo")
    public User userInfo() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private Set<Role> newSet(Set<Role> roles) {
        Set<Role> roleSet = new HashSet<>();
        for (Role role : roles) {
            if (role.getRole() != null) {
                roleSet.add(roleService.findRoleByName(role.getRole()));
            }
        }
        return roleSet;
    }
}
