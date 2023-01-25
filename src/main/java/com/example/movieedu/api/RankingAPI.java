package com.example.movieedu.api;


public class RankingAPI {
	
	public RankingAPI() {}
	
    public static String getUrl(String targetDt) {
        	

    	//Date이 들어가야 됌 
    	
        String apiURL = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=8ef8d0855390695f37c6d9af6fd08725&targetDt="+targetDt;    // JSON 결과


//        System.out.println(apiURL);
        
        String getJson = JsonReader.callURL(apiURL);
        
        return getJson;
    }

   
}