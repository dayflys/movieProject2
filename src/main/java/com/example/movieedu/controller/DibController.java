package com.example.movieedu.controller;

import com.example.movieedu.model.dao.DibDAO;
import com.example.movieedu.model.vo.CommentVO;
import com.example.movieedu.model.vo.DibVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller

public class DibController {
    @Autowired
    private DibDAO dao;
    @GetMapping("/dib/select")
    public ModelAndView list(String nickname) {
        System.out.println("comment/select 접속 성공");
        List<DibVO> diblist = dao.searchM(nickname);
        ModelAndView mav = new ModelAndView();
        if (diblist.size() != 0){
            mav.addObject("diblist", diblist);
        }
        else{
            mav.addObject("msg", "찜목록 읽기 오류 발생");
        }

        mav.setViewName("info");

        return mav;
    }
    @PostMapping("/dib/insert")
    public ModelAndView insert(DibVO vo, String moviename, String id) throws UnsupportedEncodingException {
        System.out.println("dib/insert 접속 성공");
        boolean result = dao.insertM(vo);
        ModelAndView mav = new ModelAndView();
        if (result){
            mav.addObject("msg", "해당 영화가 찜 목록에 추가되었습니다");
        } else{
            mav.addObject("msg", "찜 목록 추가 중 오류 발생");
        }
        mav.setViewName("redirect:/comment/select?x=22&y=104&moviename="+URLEncoder.encode(moviename,"UTF-8")+"&id="+id);
        return mav;
    }

    @PostMapping("/dib/delete")
    public ModelAndView delete(DibVO vo) throws UnsupportedEncodingException {
        System.out.println("dib/delete 접속 성공");
        boolean result = dao.deleteM(vo.getCnt());
        ModelAndView mav = new ModelAndView();

        if (result){
            mav.addObject("msg", "해당 영화가 찜 목록에 삭제되었습니다");

        } else {
            mav.addObject("msg", "찜 목록 삭제 중 오류 발생");
        }
        System.out.println("닉네임: " + vo.getNickname());
        System.out.println("닉네임: " + URLEncoder.encode(vo.getNickname(),"UTF-8"));

        mav.setViewName("redirect:/dib/select?nickname="+URLEncoder.encode(vo.getNickname(),"UTF-8"));
        return mav;
    }
}
