package com.example.movieedu;

import api.API;
import model.vo.MovieVO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import service.MovieService;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {
    @Autowired
    MovieService ms;

    @GetMapping("/movie/country")
    public ModelAndView country(String subject,int num){
        List<MovieVO> countries = ms.country("test",num);
        ModelAndView mav = new ModelAndView();
        ArrayList<MovieVO> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MovieVO vo = new MovieVO();
            String result = API.get(countries.get(i).getMovieNM());
            JSONObject object = new JSONObject(result);
            JSONArray items = object.getJSONArray("items");
            JSONObject item = (JSONObject) items.get(0);
            vo.setSubtitle(item.getString("subtitle"));
            vo.setRank(countries.get(i).getRank());
            vo.setMovieNM(countries.get(i).getMovieNM());
            vo.setActor(item.getString("actor"));
            vo.setImgUrl(item.getString("image").replaceAll("mit110", "mit500"));
            vo.setPubDate(Integer.parseInt(item.getString("pubDate")));
            vo.setUserRating(Double.parseDouble(item.getString("userRating")));
            results.add(vo);
        }
        mav.addObject("select",results);
        mav.addObject("subject",subject);
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("/movie/ganre")
    public ModelAndView ganre(String subject,int num) {
        List<MovieVO> ganres = ms.ganre("test", num);
        ModelAndView mav = new ModelAndView();
        ArrayList<MovieVO> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MovieVO vo = new MovieVO();
            String result = API.get(ganres.get(i).getMovieNM());
            JSONObject object = new JSONObject(result);
            JSONArray items = object.getJSONArray("items");
            JSONObject item = (JSONObject) items.get(0);
            vo.setSubtitle(item.getString("subtitle"));
            vo.setRank(ganres.get(i).getRank());
            vo.setMovieNM(ganres.get(i).getMovieNM());
            vo.setActor(item.getString("actor"));
            vo.setImgUrl(item.getString("image").replaceAll("mit110", "mit500"));
            vo.setPubDate(Integer.parseInt(item.getString("pubDate")));
            vo.setUserRating(Double.parseDouble(item.getString("userRating")));
            results.add(vo);
        }
        mav.addObject("select",results);
        mav.addObject("subject",subject);
        mav.setViewName("login");
        return mav;
    }

//    public static void 만들기(List<MovieVO> list,ArrayList<MovieVO> results){
//        for (int i = 0; i < 10; i++) {
//            MovieVO vo = new MovieVO();
//            String result = API.get(list.get(i).getMovieNM());
//            System.out.println(result);
//            JSONObject object = new JSONObject(result);
//            JSONArray items = object.getJSONArray("items");
//            JSONObject item = (JSONObject) items.get(0);
//            vo.setSubtitle(item.getString("subtitle"));
//            vo.setRank(list.get(i).getRank());
//            vo.setMovieNM(list.get(i).getMovieNM());
//            vo.setActor(item.getString("actor"));
//            vo.setImgUrl(item.getString("image").replaceAll("mit110", "mit500"));
//            vo.setPubDate(Integer.parseInt(item.getString("pubDate")));
//            vo.setUserRating(Double.parseDouble(item.getString("userRating")));
//            results.add(vo);
//        }
//    }
}
