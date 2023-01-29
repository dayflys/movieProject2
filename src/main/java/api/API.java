package api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class API {

    public static String get(String movieNM) {
        String clientId = "COS9_Ly5kVci4lqS1qBT"; //애플리케이션 클라이언트 아이디
        String clientSecret = "zxfwKlujSG"; //애플리케이션 클라이언트 시크릿
        String text = null;
        try {
            text = URLEncoder.encode(movieNM, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        try {
            String strUrl = "https://openapi.naver.com/v1/search/movie.json?query=" + text;
            URL url = new URL(strUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setConnectTimeout(600); //서버에 연결되는 Timeout 시간 설정
//            con.setReadTimeout(500);
            con.addRequestProperty("X-Naver-Client-Id", clientId); //key값 설정
            con.addRequestProperty("X-Naver-Client-Secret", clientSecret);
            con.setRequestMethod("GET");

            con.setDoOutput(false);

            StringBuilder sb = new StringBuilder();
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //Stream을 처리해줘야 하는 귀찮음이 있음.
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();
                return sb.toString();
            } else {
                System.out.println(con.getResponseMessage());
            }

        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return "";
    }

}
