package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import model.dao.CommentDAO;
import model.vo.CommentVO;


@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CommentDAO dao;
	public void init() {
		dao = new CommentDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("넘어간거는 맞아?");
		request.setCharacterEncoding("UTF-8");
		
			
		//Json정보 get body 및 Object 변환
		String body = getBody(request);
		JSONObject jsonObj;
		
				
		try {
			jsonObj = new JSONObject(body);
			System.out.println(jsonObj);
			String action = (String)jsonObj.get("action"); 

			if (action.equals("update")) {
				String movie = (String)jsonObj.get("movie");
				String content = (String)jsonObj.get("cont");
				Integer cnt = jsonObj.getInt("cnt");
				System.out.println(cnt);
				
				CommentVO vo = new CommentVO();
				vo.setMoviename(movie);
				vo.setContent(content);
				vo.setCnt(cnt);
				boolean result = dao.update(vo);
				
				if (result) {			
					request.setAttribute("msg", "글이 성공적으로 입력되었습니다.");			
				} else {
					request.setAttribute("msg", "글이 입력되지 않았습니다.");
				}	
				
				RequestDispatcher rd= request.getRequestDispatcher("select");
				rd.forward(request,response);				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
	}
	
	
	 protected void process(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	        System.out.println(getBody(req));
	    }
	    
    public static String getBody(HttpServletRequest request) throws IOException {
 
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
 
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
 
        body = stringBuilder.toString();
        return body;
    }
			
}
