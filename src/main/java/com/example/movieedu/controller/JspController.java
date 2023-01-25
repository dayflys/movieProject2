package com.example.movieedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class JspController {

    @GetMapping(value = {"/login","/"})
    public String helloString() {
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "sign-up";
    }

    @GetMapping("/detail")
    public String detail(){
        return "detail";
    }
}
