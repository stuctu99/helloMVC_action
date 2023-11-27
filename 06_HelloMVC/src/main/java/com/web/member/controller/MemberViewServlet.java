package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.common.security.AESEncryptor;
import com.web.member.model.dto.Member;
import com.web.member.model.service.MemberService;

/**
 * Servlet implementation class MemberViewServlet
 */
@WebServlet("/member/memberView.do")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String userId=request.getParameter("userId");
		
		Member m=new MemberService().selectMemberById(userId);
//		m=session.getAttribute("loginMember");
		try {
			String email=m.getEmail();
			email=AESEncryptor.decryptData(email);
			m.setEmail(email);
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			String phone=m.getPhone();
			phone=AESEncryptor.decryptData(phone);
			m.setPhone(phone);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
			request.setAttribute("member", m);
		
		
		request.getRequestDispatcher("/views/member/memberView.jsp")
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
