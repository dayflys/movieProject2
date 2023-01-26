package com.example.movieedu.controller;

import com.example.movieedu.model.dao.CommentDAO;
import com.example.movieedu.model.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentDAO dao;
    @GetMapping("/select")
    public ModelAndView list(String moviename, String id) {
        System.out.println("comment/select 접속 성공");
        List<CommentVO> list = dao.searchM(moviename);
        ModelAndView mav = new ModelAndView();
        if (list.size() != 0){
            mav.addObject("list", list);
        }
        else{
            mav.addObject("msg", "해당 영화의 댓글 없음");
        }
        System.out.println("id:" + id);

        mav.addObject("movieId", id);
        mav.setViewName("detail");

        return mav;
    }
    @PostMapping("/insert")
    public ModelAndView insert(CommentVO vo, String id) throws UnsupportedEncodingException {
        System.out.println("comment/insert 접속 성공");
        boolean result = dao.insertM(vo);
        ModelAndView mav = new ModelAndView();
        if (result){
            mav.addObject("list", dao.searchM(vo.getMoviename()));
        } else{
            mav.addObject("msg", "글 작성 중 오류 발생");
        }
        mav.addObject("movieId", id);

        mav.setViewName("redirect:/comment/select?x=22&y=104&moviename="+URLEncoder.encode(vo.getMoviename(),"UTF-8")+"&id="+id);

        return mav;
    }

    @PostMapping(value = "/update", consumes = "application/json")
    @ResponseBody
    public ModelAndView update(@RequestBody CommentVO vo, String id) {
        System.out.println("comment/update 접속 성공");
        boolean result = dao.updateM(vo);
        ModelAndView mav = new ModelAndView();

        if(result){
            mav.addObject("list", dao.searchM(vo.getMoviename()));
        }
        else{
            mav.addObject("msg","글 수정 중 오류 발생");
        }
        mav.addObject("movieId", id);
        mav.setViewName("detail");
        return mav;
    }

    @PostMapping("/delete")
    public ModelAndView delete(CommentVO vo, String id) throws UnsupportedEncodingException {
        System.out.println("comment/delete 접속 성공");
        boolean result = dao.deleteM(vo.getCnt());
        ModelAndView mav = new ModelAndView();
        if (result) {
            mav.addObject("list", dao.searchM(vo.getMoviename()));
        } else {
            mav.addObject("msg", "삭제 중 오류 발생");
        }
        mav.addObject("movieId", id);
        mav.setViewName("redirect:/comment/select?x=22&y=104&moviename="+URLEncoder.encode(vo.getMoviename(),"UTF-8")+"&id="+id);
        return mav;
    }

    @PostMapping("/like")
    public ModelAndView like(CommentVO vo, String id) throws UnsupportedEncodingException {
        System.out.println("comment/like 접속 성공");

        System.out.println("Comment vo" + vo.getLike());
        System.out.println("Comment vo" + vo.getCnt());
        boolean result = dao.likeM(vo);
        ModelAndView mav = new ModelAndView();
        if(result){
            mav.addObject("list", dao.searchM(vo.getMoviename()));
        } else {
            mav.addObject("msg", "좋아요 수정 중 오류 발생");
        }
        mav.addObject("movieId", id);
        mav.setViewName("redirect:/comment/select?x=22&y=104&moviename="+URLEncoder.encode(vo.getMoviename(),"UTF-8")+"&id="+id);
        return mav;

    }
}
