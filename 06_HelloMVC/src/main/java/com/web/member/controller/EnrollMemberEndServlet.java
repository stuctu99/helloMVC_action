package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.common.security.AESEncryptor;
import com.web.common.security.PasswordEncoder;
import com.web.member.model.dto.Member;
import com.web.member.model.service.MemberService;

/**
 * Servlet implementation class EnrollMemberEndServlet
 */
@WebServlet(name="enrollMemberEnd",
			urlPatterns="/member/enrollMemberEnd.do")
public class EnrollMemberEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollMemberEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원가입서비스처리하는 서블릿
		//1. 클라이언트가 보낸 데이터 가져오기 -> request.getParameter(),getParameterValues()
		// request.getParameterMap(), request.getParameterNames();
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		String userName=request.getParameter("userName");
		int age=Integer.parseInt(request.getParameter("age"));
		String email=request.getParameter("email");
		try {
//			String encEmail=AESEncryptor.encryptData(email);
			email=AESEncryptor.encryptData(email);
//			System.out.println(encEmail);
		}catch(Exception e) {
			e.printStackTrace();
		}
		String gender=request.getParameter("gender");
		String phone=request.getParameter("phone");
		try {
//			String encPhone=AESEncryptor.encryptData(phone);
			phone=AESEncryptor.encryptData(phone);
//			System.out.println(encPhone);
		}catch(Exception e) {
			e.printStackTrace();
		}
		String address=request.getParameter("address");
		String[] hobby=request.getParameterValues("hobby");
		
//		PasswordEncoder pe=new PasswordEncoder();
//		
//		System.out.println(pe.getSHA512(password));
	
		
		Member m=Member.builder()
				.userId(userId)
				.password(password)
				.userName(userName)
				.age(age)
				.gender(gender)
				.email(email)
				.phone(phone)
				.address(address)
				.hobby(hobby)
				.build();
		
		System.out.println(m);
		
		//2. 가져온 데이터 Member테이블에 추가하기
		int result=new MemberService().insertMember(m);
		//3. 추가한결과 사용자에게 보여주기
		//result==0 등록실패, result==1이면 등록성공
		//등록실패 : 등록실패 메세지를 출력하고, 회원가입화면으로 이동
		//등록성공 : 등록성공 메세지를 출력하고, 메인화면으로 이동
		String msg,loc;
		if(result>0) {
			msg=m.getUserId()+"님 회원가입을 축하드립니다.!";
			loc="/";
		}else {
			msg="회원가입에 실패했습니다 다시 시도하거나 관리자에게 문의하세요 :(";
			loc="/member/enrollMember.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/views/common/msg.jsp")
		.forward(request, response);
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
