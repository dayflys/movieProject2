package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CommentDAO;
import model.vo.CommentVO;


@WebServlet("/commentps")
public class commentps extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentDAO dao;
	public void init() {
		dao = new CommentDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer cnt = Integer.parseInt(request.getParameter("cnt"));
		String id = request.getParameter("id");
		String movie = request.getParameter("movie");
		Integer like = Integer.parseInt(request.getParameter("like"));
		CommentVO vo = new CommentVO();
		System.out.println(like);
		vo.setLike(like);
		vo.setCnt(cnt);
		boolean result = dao.updateLike(vo);
		if (result) {			
			request.setAttribute("msg","좋아요가 성공적으로 올랐습니다.");			
		} else {
			request.setAttribute("msg","좋아요 올리는데 실패 하였습니다.");
		}
		request.setAttribute("id", id);
		request.setAttribute("movie", movie);
		RequestDispatcher rd= request.getRequestDispatcher("select");
		rd.forward(request,response);	
	}

}
