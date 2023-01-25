package com.example.movieedu.api;


public class RankingWeekAPI {
	
	public RankingWeekAPI() {}
	
    public static String getUrl(String targetDt) {
        	

    	//Date이 들어가야 됌 
    	
        String apiURL = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json?key=8778dd5ff0d67c0db92ad89d65467256&weekGb=0&targetDt="+targetDt;    // JSON 결과


        System.out.println(apiURL);
        
        String getJson = JsonReader.callURL(apiURL);
        
        return getJson;
    }

   
}