package com.web.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.admin.model.service.AdminService;
import com.web.member.model.dto.Member;

/**
 * Servlet implementation class AjaxSearchMemberIdServlet
 */
@WebServlet("/member/searchId.do")
public class AjaxSearchMemberIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxSearchMemberIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword=request.getParameter("keyword");

		List<Member> searchMember=new AdminService()
				.searchMemberByKeyword("userid", keyword, 1, 20);
		String csv="";
		for(int i=0;i<searchMember.size();i++) {
			if(i!=0)csv+=",";
			csv+=searchMember.get(i).getUserId();
		}
		
		response.setContentType("text/csv;charset=utf-8");
		response.getWriter().print(csv);
		
		
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
