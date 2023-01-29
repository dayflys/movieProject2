package com.example.movieedu;

import model.dao.memberDAO;
import model.vo.MovieVO;
import model.vo.memberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MemberController {
    @Autowired
    memberDAO dao;

    @PostMapping("/member/register")
    public ModelAndView MemberRegister(String nickname,String id,String pwd,String name,String gender,String email){
        memberVO vo = new memberVO();
        vo.setNickname(nickname);
        vo.setId(id);
        vo.setPwd(pwd);
        vo.setName(name);
        vo.setGender(gender);
        vo.setEmail(email);
        boolean result = dao.register(vo);
        ModelAndView mav = new ModelAndView();
        if(result){
            mav.addObject("msg", "회원 가입이 완료되었습니다.");
        }else{
            mav.addObject("msg", "회원 가입에 실패하였습니다.");
        }
        mav.setViewName("login");
        return  mav;
    }

    @GetMapping(value="/member/delete", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String MemberDelete(String nickname,HttpSession s){
        boolean result = dao.delete(nickname);
        System.out.println();
        String jsonStr;
        if (result) {
            s.removeAttribute("user");
            jsonStr = "{ \"result\": true }";
        } else {
            jsonStr = "{ \"result\": false }";
        }
        return jsonStr;
    }

    @PostMapping("/member/login")
    public ModelAndView MemberLogin(String id, String pwd,HttpSession s){
        memberVO vo = new memberVO();
        vo.setId(id);
        vo.setPwd(pwd);
        memberVO user = dao.login(vo);
        ModelAndView mav = new ModelAndView();
        if(user != null) {
            s.setAttribute("user", user);
            mav.addObject("msg", "로그인이 완료되었습니다.");
        }else{
            mav.addObject("msg", "로그인에 실패하였습니다.");
        }
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("/member/logout")
    public String MemberLogout(HttpSession s){
        s.removeAttribute("user");
        return "login";
    }

    @GetMapping("/member/info")
    public String MemberInfo(){
        return "info2";
    }
}
