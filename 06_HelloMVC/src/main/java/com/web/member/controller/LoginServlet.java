package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.member.model.dto.Member;
import com.web.member.model.service.MemberService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="login",urlPatterns="/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 클라이언트가 보낸 아이디와 패스워드를 받아온다.
		//getParameter()||getParameterValues()
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		String saveId=request.getParameter("saveId");
		System.out.println(saveId);
		if(saveId!=null) {
			//아이디를 저장
			Cookie c=new Cookie("saveId",userId);
			c.setMaxAge(60*60*24);
			response.addCookie(c);
		}else {
			Cookie c=new Cookie("saveId","");
			c.setMaxAge(0);
			response.addCookie(c);
		}
//		System.out.println(userId+" "+password);
		//2. DB의 member테이블에서 보낸 아이디와 패스워드가 일치하는 회원을 가져온다.
		Member m=new MemberService().selectMemberByIdAndPw(userId,password);
		System.out.println(m);
		//m이 null이면 로그인 실패
		//m이 null이 아니면 로그인 성공
		if(m!=null) {
			//로그인 성공
			//HttpSession에 데이터를 저장
			HttpSession session=request.getSession();
			session.setAttribute("loginMember", m);
			response.sendRedirect(request.getContextPath());
		}else {
			//로그인 실패
			request.setAttribute("msg","아이디나 패스워드가 일치하지않습니다.");
			request.setAttribute("loc","/");
			request.getRequestDispatcher("/views/common/msg.jsp")
			.forward(request,response);
		}
		
		//3. 결과를 출력한다(응답페이지를 선택한다.)
		
//		RequestDispatcher rd=request.getRequestDispatcher("/");
//		rd.forward(request, response);
		
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
