package com.web.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.model.dto.Board;
import com.web.board.model.dto.BoardComment;
import com.web.board.model.service.BoardService;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView.do")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no=Integer.parseInt(request.getParameter("no"));
		
		Cookie[] cookies=request.getCookies();
		String readBoard="";
		boolean readResult=false;
		for(Cookie c:cookies) {
			String name=c.getName();
			if(name.equals("readBoard")) {
				readBoard=c.getValue();
				if(readBoard.contains("|"+no+"|")) {
					readResult=true;
				}
				break;
			}
		}
		
		if(!readResult) {
			Cookie c=new Cookie("readBoard",readBoard+"|"+no+"|");
			c.setMaxAge(60*60*24);
			response.addCookie(c);
		}
		
		
		
		Board b=new BoardService().selectBoardByNo(no,readResult);
		List<BoardComment> comments=new BoardService().selectBoardComment(no);
		
		request.setAttribute("board", b);
		request.setAttribute("comments", comments);
		request.getRequestDispatcher("/views/board/boardView.jsp")
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
