package com.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/u/{id}")
    public String home(@PathVariable Long id) {

        return "user/home";
    }
}
