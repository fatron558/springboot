package com.kireev.springboot.demo.controllers;

import com.kireev.springboot.demo.models.Role;
import com.kireev.springboot.demo.models.User;
import com.kireev.springboot.demo.service.RoleService;
import com.kireev.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userAuth", user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getAll());
        return "admin";
    }

    @PostMapping("/admin/add")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(name ="allRoles", required = false) String[] roles) {
        if (roles != null) {
            Set<Role> rolesSet = new HashSet<>();
            for (String role : roles) {
                rolesSet.add(roleService.findRoleByName(role));
            }
            user.setRoles(rolesSet);
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/edit")
    public String editUser(@ModelAttribute("user") User user,
                           @RequestParam(name = "allRoles", required = false) String[] roles) {
        if (roles != null) {
            Set<Role> rolesSet = new HashSet<>();
            for (String role : roles) {
                rolesSet.add(roleService.findRoleByName(role));
            }
            user.setRoles(rolesSet);
        }
        userService.editUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete")
    public String delete(@RequestParam(name = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String userInfo(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "info";
    }
}
