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
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/admin/memberList.do")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		int numPerpage=5;
		//DB web계정의 member테이블의 전체 데이터를 가져오기
		List<Member> members=new AdminService().searchMemberList(cPage,numPerpage);
		
		request.setAttribute("members",members);
		
		//pageBar만들기~~ 재미있겠다!! 아싸
		//1. 전체 데이터를 가져와 저장하기
		int totalData=new AdminService().selectMemberCount();
		//2. 전체 페이지수를 저장하기
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		//3. 페이지바에 출력될 번호의 갯수 설정
		int pageBarSize=5;
		//4. pageBar에 출력될 번호의 시작번호
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		//5. pageBar에 출력될 번호의 끝번호
		int pageEnd=pageNo+pageBarSize-1;
		
		String pageBar="<ul class='pagination justify-content-center'>";
		
		if(pageNo==1) {
			pageBar+="<li class='page-item disabled'><a class='page-link' href='#'>이전</a></li>";
		}else {
			pageBar+="<li class='page-item'><a class='page-link' href='"+request.getRequestURI()
						+"?cPage="+(pageNo-1)+"'>이전</a></li>";
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<li class='page-item active'><a class='page-link' href='#'>"+pageNo+"</a></li>";
			}else {
				pageBar+="<li class='page-item'><a class='page-link' href='"+request.getRequestURI()
						+"?cPage="+pageNo+"'>"+pageNo+"</a></li>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<li class='page-item disabled'><a class='page-link' href='#'>다음</a></li>";
		}else {
			pageBar+="<li class='page-item'><a class='page-link' href='"+request.getRequestURI()
					+"?cPage="+pageNo+"'>다음</a></li>";
		}
		pageBar+="</ul>";	
		request.setAttribute("pageBar", pageBar);		
		
		
		
		
		
		
		//가져온 데이터를 화면에 출력하기-> 전체데이터를 출력하는 화면선택하기
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
