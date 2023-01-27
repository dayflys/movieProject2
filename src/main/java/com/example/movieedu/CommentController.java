package com.example.movieedu;

import model.dao.CommentDAO;
import model.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentDAO dao;

    @GetMapping("/select")
    public ModelAndView list(String moviename, String id, String curMovie,String msg, HttpSession s) {
        System.out.println("comment/select 접속 성공");
        List<CommentVO> list = dao.searchM(moviename);
        ModelAndView mav = new ModelAndView();
        if (list.size() != 0){
            mav.addObject("list", list);
        }
        if(msg != null){
            mav.addObject("msg", msg);
        }
        s.setAttribute("movieList",s.getAttribute(curMovie));
        System.out.println(curMovie);
        System.out.println(s.getAttribute(curMovie));
        s.setAttribute("curMovie",curMovie);
        mav.addObject("movieId", id);
        mav.setViewName("detail");

        return mav;
    }

    @PostMapping("/insert")
    public ModelAndView insert(CommentVO vo, String id,String curMovie, HttpSession s) throws UnsupportedEncodingException {
        System.out.println("시작 :" + curMovie);
        s.setAttribute("curMovie",curMovie);
        System.out.println("중간2 : " + s.getAttribute(curMovie));
        System.out.println("comment/insert 접속 성공");
        boolean result = dao.insertM(vo);
        ModelAndView mav = new ModelAndView();
        if (result){
            mav.addObject("list", dao.searchM(vo.getMoviename()));
        } else{
            mav.addObject("msg", "글 작성 중 오류 발생");
        }
        mav.addObject("movieId", id);
        System.out.println("중간 : " + curMovie);
        System.out.println("중간2 : " + s.getAttribute(curMovie));
        s.setAttribute("movieList",s.getAttribute(curMovie));

        mav.setViewName("redirect:/comment/select?x=22&y=104&moviename="+URLEncoder.encode(vo.getMoviename(),"UTF-8")+"&id="+id+"&curMovie="+curMovie);

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
    public ModelAndView delete(CommentVO vo, String id,String curMovie, HttpSession s) throws UnsupportedEncodingException {
        System.out.println("comment/delete 접속 성공");
        boolean result = dao.deleteM(vo.getCnt());
        ModelAndView mav = new ModelAndView();
        if (result) {
            mav.addObject("list", dao.searchM(vo.getMoviename()));
        } else {
            mav.addObject("msg", "삭제 중 오류 발생");
        }
        mav.addObject("movieId", id);
        s.setAttribute("movieList",s.getAttribute(curMovie));
        s.setAttribute("curMovie",curMovie);
        mav.setViewName("redirect:/comment/select?x=22&y=104&moviename="+URLEncoder.encode(vo.getMoviename(),"UTF-8")+"&id="+id+"&curMovie="+curMovie);
        return mav;
    }

    @PostMapping("/like")
    public ModelAndView like(CommentVO vo, String id, String curMovie, HttpSession s) throws UnsupportedEncodingException {
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
        s.setAttribute("movieList",s.getAttribute(curMovie));
        s.setAttribute("curMovie",curMovie);
        mav.setViewName("redirect:/comment/select?x=22&y=104&moviename="+URLEncoder.encode(vo.getMoviename(),"UTF-8")+"&id="+id+"&curMovie="+curMovie);
        return mav;

    }
}
