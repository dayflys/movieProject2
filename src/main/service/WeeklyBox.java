package service;

import api.NaverAPI;
import api.RankingAPI;
import api.RankingWeekAPI;
import model.vo.MovieVO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

@Service
public class WeeklyBox {

    public ArrayList<MovieVO> getWeeklyBox(HttpSession s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        Calendar w = Calendar.getInstance();
        w.add(Calendar.DATE, -7);
        w.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String weeklydate = sdf.format(w.getTime());
        String weekly = RankingWeekAPI.getUrl(weeklydate);

        ArrayList<MovieVO> movielist = new ArrayList<MovieVO>();
        try {
            JSONObject weeklybr = new JSONObject(weekly);
            JSONObject wbr = weeklybr.getJSONObject("boxOfficeResult");
            JSONArray wary = wbr.getJSONArray("weeklyBoxOfficeList");

            String movieNm;
            for (int i = 0; i < wary.length(); i++) {
                MovieVO movieVO = new MovieVO();
                movieNm = wary.getJSONObject(i).getString("movieNm");

                String nAPI = NaverAPI.getUrl(movieNm);
                JSONObject nr = new JSONObject(nAPI);
                JSONArray items = nr.getJSONArray("items");

                movieVO.setMovieNM(movieNm);

                movieVO.setRank(wary.getJSONObject(i).getInt("rank"));

                movieVO.setPubDate(items.getJSONObject(0).getInt("pubDate"));
                movieVO.setSubtitle(items.getJSONObject(0).getString("subtitle"));
                movieVO.setUserRating(items.getJSONObject(0).getDouble("userRating"));
                movieVO.setDirector(items.getJSONObject(0).getString("director"));
                movieVO.setActor(items.getJSONObject(0).getString("actor"));
                movieVO.setImgUrl(items.getJSONObject(0).getString("image"));

                movielist.add(movieVO);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movielist;
    }
}
