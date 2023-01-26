package com.example.movieedu;

import api.API;
import model.vo.MovieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import service.DailyBox;
import service.WeeklyBox;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
public class JspController {

    @Autowired
    DailyBox dailyBox;

    @Autowired
    WeeklyBox weeklyBox;

    @GetMapping("/")
    public String callAPI(HttpSession s) throws InterruptedException {

        ArrayList<MovieVO> daily = new ArrayList<>();
        daily.addAll(dailyBox.getDailyBox(s));
        Thread.sleep(1000);
        ArrayList<MovieVO> weekly = new ArrayList<>();
        weekly.addAll(weeklyBox.getWeeklyBox(s));

        s.setAttribute("daily", daily);
        s.setAttribute("weekly", weekly);

        return "login";
    }

    @GetMapping("/login")
    public String login() {
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

    @GetMapping("/userPage")
    public String userPage(){
        return "userPage";
    }
}
