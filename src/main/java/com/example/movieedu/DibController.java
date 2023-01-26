package com.example.movieedu;

import model.dao.DibDAO;
import model.vo.DibVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;

@Controller

public class DibController {
    @Autowired
    private DibDAO dao;

    @PostMapping("/dib/insert")
    public ModelAndView insert(DibVO vo) {
        System.out.println("dib/insert 접속 성공");
        boolean result = dao.insertM(vo);
        ModelAndView mav = new ModelAndView();
        if (result){
            mav.addObject("msg", "해당 영화가 찜 목록에 추가되었습니다");
        } else{
            mav.addObject("msg", "글 작성 중 오류 발생");
        }
        return mav;
    }




}
