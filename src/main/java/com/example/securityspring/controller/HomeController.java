package com.example.securityspring.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("dashboard")
    public String dashboard() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("=========================");
        System.out.println(email);
        System.out.println("=========================");
        return "dashboard";
    }

    @GetMapping("admin")
    public String admin() {
        return "admin";
    }
}
