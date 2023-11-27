package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.member.model.dto.Member;
import com.web.member.model.service.MemberService;

/**
 * Servlet implementation class IdDuplicateServlet
 */
@WebServlet("/member/idDuplicate.do")
public class IdDuplicateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdDuplicateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//아이디가 중복인지를 확인해주는 서비스
		//클라이언트가 전달한 아이디값이 DB(membertable)에 있는지 확인
		String userId=request.getParameter("userId");
		Member m=new MemberService().selectMemberById(userId);
		
		//확인결과를 저장
		// null이면 사용이 가능, null이 아니면 사용이 불가능
		request.setAttribute("result", m==null);
		//아이디 중복확인 결과를 출력해주는 화면출력
		request.getRequestDispatcher("/views/member/idduplicate.jsp")
		.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
