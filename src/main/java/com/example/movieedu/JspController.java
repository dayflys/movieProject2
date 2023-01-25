package com.example.movieedu;

import api.NaverAPI;
import api.RankingAPI;
import api.RankingWeekAPI;
import model.vo.MovieVO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


@Controller
public class JspController {

    @GetMapping("/")
    public String callAPI(HttpSession s) {


        try {

           SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
           Calendar d = Calendar.getInstance();
           Calendar w = Calendar.getInstance();
           d.add(Calendar.DATE, -1);
           w.add(Calendar.DATE, -7);
           w.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
           String dailydate = sdf.format(d.getTime());
           String weeklydate = sdf.format(w.getTime());

           String daily = RankingAPI.getUrl(dailydate);
           String weekly = RankingWeekAPI.getUrl(weeklydate);

           JSONObject dailybr = new JSONObject(daily);
           JSONObject dbr = dailybr.getJSONObject("boxOfficeResult");
           JSONArray dary = dbr.getJSONArray("dailyBoxOfficeList");

           JSONObject weeklybr = new JSONObject(weekly);
           JSONObject wbr = weeklybr.getJSONObject("boxOfficeResult");
           JSONArray wary = wbr.getJSONArray("weeklyBoxOfficeList");

           ArrayList<MovieVO> movielist = new ArrayList<MovieVO>();
           String movieNm;

           for (int i = 0; i < dary.length(); i++) {

               s.setAttribute("movie", new MovieVO());
               MovieVO movieVO = (MovieVO) s.getAttribute("movie");
               movieNm = dary.getJSONObject(i).getString("movieNm");

               String nAPI = NaverAPI.getUrl(movieNm);

               JSONObject nr = new JSONObject(nAPI);
               JSONArray items = nr.getJSONArray("items");

               movieVO.setRank(dary.getJSONObject(i).getInt("rank"));

               movieVO.setPubDate(items.getJSONObject(0).getInt("pubDate"));
               movieVO.setSubtitle(items.getJSONObject(0).getString("subtitle"));
               movieVO.setUserRating(items.getJSONObject(0).getDouble("userRating"));
               movieVO.setDirector(items.getJSONObject(0).getString("director"));
               movieVO.setActor(items.getJSONObject(0).getString("actor"));
               movieVO.setImgUrl(items.getJSONObject(0).getString("image"));

               movielist.add(movieVO);

           }
           Thread.sleep(1000);

           for (int i = 10; i < (dary.length() + wary.length()); i++) {
               s.setAttribute("movie", new MovieVO());
               MovieVO movieVO = (MovieVO) s.getAttribute("movie");
               movieNm = wary.getJSONObject((i - dary.length())).getString("movieNm");

               String nAPI = NaverAPI.getUrl(movieNm);

               JSONObject nr = new JSONObject(nAPI);
               JSONArray items = nr.getJSONArray("items");

               movieVO.setMovieNM(movieNm);
               movieVO.setRank(wary.getJSONObject((i - dary.length())).getInt("rank"));

               movieVO.setPubDate(items.getJSONObject(0).getInt("pubDate"));
               movieVO.setSubtitle(items.getJSONObject(0).getString("subtitle"));
               movieVO.setUserRating(items.getJSONObject(0).getDouble("userRating"));
               movieVO.setDirector(items.getJSONObject(0).getString("director"));
               movieVO.setActor(items.getJSONObject(0).getString("actor"));
               movieVO.setImgUrl(items.getJSONObject(0).getString("image"));


               movielist.add(movieVO);
           }

        s.setAttribute("movielist", movielist);

        } catch (JSONException | InterruptedException e) {
            e.printStackTrace();
        }
        return "login";
    }
}

