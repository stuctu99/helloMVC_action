package com.web.admin.controller;

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
 * Servlet implementation class SearchMemberServlet
 */
@WebServlet("/admin/searchMember")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자가 보낸 검색항목, 내용을 받아서 DB에서 일치하는 회원을 조회하는 기능
		String type=request.getParameter("searchType");
		String keyword=request.getParameter("searchKeyword");
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		int numPerpage=5;
				
		
		List<Member> result=new AdminService().searchMemberByKeyword(type,keyword,cPage,numPerpage);
		int totalData=new AdminService().selectMemberByKeywordCount(type,keyword);
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		String pageBar="<ul class='pagination justify-content-center'>";
		if(pageNo==1) {
			pageBar+="<li class='page-item disabled'>";
			pageBar+="<a class='page-link' href='#'>이전</a>";
			pageBar+="</li>";
		}else {
			pageBar+="<li class='page-item'>";
			pageBar+="<a class='page-link' href='"
					 +request.getRequestURI()
					 +"?searchType="+type
					 +"&searchKeyword="+keyword
					 +"&cPage="+(pageNo-1)
					 +"'>이전</a>";
			pageBar+="</li>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<li class='page-item active'>";
				pageBar+="<a class='page-link' href='#'>"+pageNo+"</a>";
				pageBar+="</li>";
			}else {
				pageBar+="<li class='page-item'>";
				pageBar+="<a class='page-link' href='"
						 +request.getRequestURI()
						 +"?searchType="+type
						 +"&searchKeyword="+keyword
						 +"&cPage="+(pageNo)
						 +"'>"+pageNo+"</a>";
				pageBar+="</li>";
			}
			pageNo++;
		}
		if(pageNo>totalPage) {
			pageBar+="<li class='page-item disabled'>";
			pageBar+="<a class='page-link' href='#'>다음</a>";
			pageBar+="</li>";
		}else {
			pageBar+="<li class='page-item'>";
			pageBar+="<a class='page-link' href='"
					 +request.getRequestURI()
					 +"?searchType="+type
					 +"&searchKeyword="+keyword
					 +"&cPage="+(pageNo)
					 +"'>다음</a>";
			pageBar+="</li>";
		}
		pageBar+="</ul>";
		
		request.setAttribute("pageBar",pageBar);
		
		request.setAttribute("members", result);
		
		
		request.getRequestDispatcher("/views/member/memberList.jsp")
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
