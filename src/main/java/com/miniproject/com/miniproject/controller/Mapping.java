package com.miniproject.com.miniproject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by STR02119 on 6/19/2017.
 */
@Controller
public class Mapping {ๆไำๆไ

    @Value("${home.title:test}")
    private String title = "";

    @RequestMapping("/")
    public String homevoid(Map<String, Object> model) {
        model.put("title", this.title);
        return "home";
    }

    @RequestMapping("/home")
    public String Home (){
        return "home";
    }
}
