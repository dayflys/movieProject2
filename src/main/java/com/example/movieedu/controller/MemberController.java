package com.example.movieedu.controller;

import com.example.movieedu.model.dao.memberDAO;
import com.example.movieedu.model.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {
    @Autowired
    memberDAO dao;

    @PostMapping("/member/register")
    public ModelAndView MemberRegister(String nickname,String id,String pwd,String name,String gender,String email){
        MemberVO vo = new MemberVO();
        vo.setNickname(nickname);
        vo.setIdent(id);
        vo.setPwd(pwd);
        vo.setUsername(name);
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
        MemberVO vo = new MemberVO();
        vo.setIdent(id);
        vo.setPwd(pwd);
        System.out.println("member vo : "+ vo.getIdent());
        System.out.println("member vo : "+ vo.getPwd());

        MemberVO user = dao.login(vo);

        System.out.println("member vo : "+ user);
/*        System.out.println("member vo : "+ user.getId());
        System.out.println("member vo : "+ user.getNickname());*/
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
}
