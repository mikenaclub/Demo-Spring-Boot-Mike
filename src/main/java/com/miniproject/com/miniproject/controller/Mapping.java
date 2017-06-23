package com.miniproject.com.miniproject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.stream.StreamSupport;

/**
 * Created by STR02119 on 6/19/2017.
 */
@Controller
public class Mapping {

    @Value("${home.title:Home - MiniProjectMike}")
    private String title;
    @Value("${home.user:User - MiniProjectMike}")
    private String user;
    @Value("${home.error:Eror - MiniProjectMike}")
    private String error;

    @RequestMapping("/")
    public String homevoid(Map<String, Object> model) {
        Collection<? extends GrantedAuthority> roleuser = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (roleuser.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            model.put("user", this.user);
            return "/user";
        } else {
            model.put("title", this.title);
            return "/home";
        }
    }

    @RequestMapping("/home")
    public String Home(Map<String, Object> model) {
        model.put("title", this.title);
        return "/home";
    }

    @RequestMapping("/user")
    public String User(Map<String, Object> model) {
        model.put("user", this.user);
        return "/user";
    }

    @RequestMapping("/403")
    public String error403(Map<String, Object> model) {
        model.put("error", this.error);
        return "/error/403";
    }
}
