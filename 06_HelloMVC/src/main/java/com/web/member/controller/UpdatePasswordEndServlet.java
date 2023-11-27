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
 * Servlet implementation class UpdatePasswordEndServlet
 */
@WebServlet(name="updatePassword",urlPatterns="/member/updatepasswordend.do")
public class UpdatePasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		String passwordNew=request.getParameter("password_new");
		
		//1. 현재비밀번호가 맞는지 확인
		Member m=new MemberService().selectMemberByIdAndPw(userId,password);
		String msg="변경실패 다시 시도하세요! :(",loc="/member/updatePassword.do?userId="+userId;
		if(m!=null) {
		//2. 일치하면 비밀번호를 변경
			int result=new MemberService().updatePassword(userId,passwordNew);
			if(result>0) {
			// -> 변경완료되면 변경완료 메세지 후 창 자동으로 닫기, 로그아웃 후 메인화면으로 이동
				msg="변경이 완료되었습니다. 다시 로그인해주세요!";
				loc="/";
				String script="opener.location.replace('"+request.getContextPath()+"/logout.do');"
						+ "close();";
				request.setAttribute("script", script);
			}
			// -> 변경실패하면 변경실패 메세지 출력 후 비밀번호 변경화면으로 이동
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc",loc);
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
