package com.example.movieedu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TestServlet{

    @GetMapping("/login")
    public String helloString() {
        return "login";
    }
}
