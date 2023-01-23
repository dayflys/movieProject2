package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CommentDAO;
import model.vo.CommentVO;


@WebServlet("/select")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("id", request.getParameter("id"));
		
		String movie = request.getParameter("movie"); // 영화이름
		System.out.println(movie);
		CommentDAO dao = new CommentDAO();

		ArrayList<CommentVO> commentList = dao.select(movie);
		String view = "";
		
		if(commentList.size() != 0) {
			request.setAttribute("commentlist", commentList);
			view = "detail.jsp";
		} else {
			request.setAttribute("msg", " Comment 테이블에 저장된 내용이 없습니다.");
			view = "detail.jsp"; // 에러페이지
		}		
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		
		System.out.println("select comment"+commentList);		
		rd.forward(request, response);		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String movie = (String) request.getAttribute("movie"); // 영화이름
		System.out.println(movie);
		CommentDAO dao = new CommentDAO();

		ArrayList<CommentVO> commentList = dao.select(movie);
		String view = "";
		
		if(commentList.size() != 0) {
			request.setAttribute("commentlist", commentList);
			view = "detail.jsp";
		} else {
			request.setAttribute("msg", " Comment 테이블에 저장된 내용이 없습니다.");
			view = "detail.jsp"; // 에러페이지
		}		
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		
		System.out.println("select comment"+commentList);		
		rd.forward(request, response);		
		
	}

}
