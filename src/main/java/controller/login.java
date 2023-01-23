package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.memberDAO;
import model.vo.memberVO;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		HttpSession session = request.getSession();
		memberDAO dao = new memberDAO();
		ArrayList<memberVO> truely = dao.search(id,pwd);
		memberVO falsely = new memberVO();
		if(truely.get(0).getNickname().equals("")) {
			session.setAttribute("mem", falsely);
		}else {
			session.setAttribute("mem", truely.get(0));
		}
		
		response.sendRedirect("/reprotype/login.jsp");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String logout = request.getParameter("action");
		HttpSession session = request.getSession();
		if(logout.equals("logout")) {
			session.removeAttribute("mem");
		}
		response.sendRedirect("/reprotype/login.jsp");
	}

}
