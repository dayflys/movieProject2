package com.example.movieedu.service;

import com.example.movieedu.api.RankingAPI;
import com.example.movieedu.model.vo.MovieVO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    public List<MovieVO> country(String date, int num){
        String query = "";
        date = "20230124";
        if(num == 1) {
            query = date + "&repNationCd=F"; // 외국 영화
        }else{
            query = date + "&repNationCd=K"; // 한국 영화
        }
        return JsonParsing(query);
    }
    public List<MovieVO> ganre(String date, int num){
        String query = "";
        date = "20230123";
        if(num == 1) {
            query = date + "&multiMovieYn=Y"; // 다양성 영화
        }else{
            query = date + "&multiMovieYn=N"; //  영화
        }
        return JsonParsing(query);
    }

    public static List<MovieVO> JsonParsing(String query){
        List<MovieVO> results = new ArrayList<MovieVO>();
        String result = RankingAPI.getUrl(query);
        JSONObject jObject = new JSONObject(result);
        JSONObject boxOfficeResult = jObject.getJSONObject("boxOfficeResult");
        JSONArray dailyBoxOfficeList = boxOfficeResult.getJSONArray("dailyBoxOfficeList");
        System.out.println("1"+dailyBoxOfficeList+"\n");
        for(int i = 0 ; i<dailyBoxOfficeList.length() ; i++) {
            MovieVO vo = new MovieVO();
            JSONObject obj = dailyBoxOfficeList.getJSONObject(i);
            String movieNm = obj.getString("movieNm");
            String rank = obj.getString("rnum");

            if (vo != null) {
                vo.setMovieNM(movieNm);
                vo.setRank(Integer.parseInt(rank));
                results.add(vo);
            }
        }
        return results;
    }
}