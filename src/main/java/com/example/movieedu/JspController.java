package com.example.movieedu;

import api.API;
import model.vo.MovieVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class JspController {

    @GetMapping("/")
    public String first(){

        //시간을 만들어야됌 전날 날짜, 그 주 월요일 날짜 두 개
        // time 2개

//        RankingAPI(time1); // 일별 박스오피스 영화 이름 리스트 꺼내야 됌
//        RankingWeekAPI(time2); // 주간 박스오피스 영화 이름 리스트 꺼내야 됌 <- 시간
        // json 형식으로 parsing 형식을 고쳐줘야 한다.
        // 자바 json 파싱
        // Map<String, int|String> 2개
        //for(String name: Map.keyset()){
//        API.get(이름들);
        // MovieVO안에 넣고 setter
        // MovieVO를 session 안에 넣고

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
