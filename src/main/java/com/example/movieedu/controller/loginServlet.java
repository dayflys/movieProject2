package com.example.movieedu.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.movieedu.api.NaverAPI;
import com.example.movieedu.api.RankingAPI;
import com.example.movieedu.api.RankingWeekAPI;
import com.example.movieedu.model.vo.MovieVO;


@WebServlet("/log")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// search/NaverAPI.html 에서 보낸 Get요청
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			// 오늘 날짜
			Date today = new Date ();
			System.out.println(today);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String targetDay = sdf.format(today);
			System.out.println("포맷 후 :" + targetDay);

			// 어제날짜
			Calendar c1 = Calendar.getInstance();
			c1.add(Calendar.DATE, -2);
			String yesterday = sdf.format(c1.getTime());

			// 날짜에 따른 박스오피스 com.example.movieedu.api 요청
			//String boxOffRes = RankingAPI.getUrl(targetDay);
			String boxOffRes = RankingAPI.getUrl(yesterday);
			String boxResWeek = RankingWeekAPI.getUrl(yesterday);

			// 박스 오피스 JSON Parsing
			JSONObject jobj2 = new JSONObject(boxOffRes);
			JSONObject BR = jobj2.getJSONObject("boxOfficeResult");
			JSONArray dBOL = BR.getJSONArray("dailyBoxOfficeList");
			//주간 결과 json parsing
			JSONObject br = new JSONObject(boxResWeek);
			JSONObject brobj = br.getJSONObject("boxOfficeResult");
			JSONArray brarray = brobj.getJSONArray("weeklyBoxOfficeList");


			System.out.println("일간:" + dBOL);
			System.out.println("주간:" + brarray);

			HttpSession session = request.getSession();
			ArrayList<MovieVO> movielist = new ArrayList<MovieVO>();
			String movieNm;
			for (int i=0; i<dBOL.length();i++) {
				session.setAttribute("movie", new MovieVO());
				MovieVO movieVO = (MovieVO)session.getAttribute("movie");
				movieNm = dBOL.getJSONObject(i).getString("movieNm");



				// 무비 네임 기반 검색..... (썸네일 및 정보 가져오기)
				String naverRes = NaverAPI.getUrl(movieNm);
				// Naver API JSON parsing
				JSONObject jobj1 = new JSONObject(naverRes);
				JSONArray items = jobj1.getJSONArray("items");


				// movieVO setting (Naver의 경우 영화진흥원의 결과중 가장 첫번째 결과에서 뽑음)
				movieVO.setMovieNM(movieNm);
				movieVO.setRank(dBOL.getJSONObject(i).getInt("rank"));
				// 여기부터 Naver API 결과
				movieVO.setPubDate(items.getJSONObject(0).getInt("pubDate"));
				movieVO.setSubtitle(items.getJSONObject(0).getString("subtitle"));
				movieVO.setUserRating(items.getJSONObject(0).getDouble("userRating"));
				movieVO.setDirector(items.getJSONObject(0).getString("director"));
				movieVO.setActor(items.getJSONObject(0).getString("actor"));
				movieVO.setImgUrl(items.getJSONObject(0).getString("image"));


				movielist.add(movieVO);
			}


			Thread.sleep(1000); //1초 대기 Naver API 초당 10개제한으로 방지




			// 주간결과 VO객체 저장
			for (int i=10; i<(dBOL.length()+brarray.length());i++) {
				session.setAttribute("movie", new MovieVO());
				MovieVO movieVO = (MovieVO)session.getAttribute("movie");
				movieNm = brarray.getJSONObject((i-dBOL.length())).getString("movieNm");
				// 무비 네임 기반 검색..... (썸네일 및 정보 가져오기)
				String naverRes = NaverAPI.getUrl(movieNm);

				// Naver API JSON parsing
				JSONObject jobj1 = new JSONObject(naverRes);
				JSONArray items = jobj1.getJSONArray("items");


				// movieVO setting (Naver의 경우 영화진흥원의 결과중 가장 첫번째 결과에서 뽑음)
				movieVO.setMovieNM(movieNm);
				movieVO.setRank(brarray.getJSONObject((i-dBOL.length())).getInt("rank"));
				// 여기부터 Naver API 결과
				movieVO.setPubDate(items.getJSONObject(0).getInt("pubDate"));
				movieVO.setSubtitle(items.getJSONObject(0).getString("subtitle"));
				movieVO.setUserRating(items.getJSONObject(0).getDouble("userRating"));
				movieVO.setDirector(items.getJSONObject(0).getString("director"));
				movieVO.setActor(items.getJSONObject(0).getString("actor"));
				movieVO.setImgUrl(items.getJSONObject(0).getString("image"));


				movielist.add(movieVO);
			}


			session.setAttribute("movielist", movielist);
			response.setContentType("text/html;charset=utf-8");
			request.setAttribute("boxOffUrl", boxOffRes);

			String view = "";
			view = "/WEB-INF/views/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
//			response.sendRedirect(view);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {

			e.printStackTrace();

		}

	}


}
