package service;

import api.NaverAPI;
import api.RankingAPI;
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
public class DailyBox {
    public ArrayList<MovieVO> getDailyBox(HttpSession s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        Calendar d = Calendar.getInstance();
        d.add(Calendar.DATE, -1);
        String dailydate = sdf.format(d.getTime());
        String daily = RankingAPI.getUrl(dailydate);

        ArrayList<MovieVO> movielist = new ArrayList<MovieVO>();
        try {
            JSONObject dailybr = new JSONObject(daily);
            JSONObject dbr = dailybr.getJSONObject("boxOfficeResult");
            JSONArray dary = dbr.getJSONArray("dailyBoxOfficeList");

            String movieNm;
            for (int i = 0; i < dary.length(); i++) {
                MovieVO movieVO = new MovieVO();
                movieNm = dary.getJSONObject(i).getString("movieNm");

                String nAPI = NaverAPI.getUrl(movieNm);
                JSONObject nr = new JSONObject(nAPI);
                JSONArray items = nr.getJSONArray("items");

                movieVO.setMovieNM(movieNm);

                movieVO.setRank(dary.getJSONObject(i).getInt("rank"));

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
