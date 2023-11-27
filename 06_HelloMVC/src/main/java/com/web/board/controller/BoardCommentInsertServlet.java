package com.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.model.dto.BoardComment;
import com.web.board.model.service.BoardService;

/**
 * Servlet implementation class BoardCommentInsertServlet
 */
@WebServlet("/board/insertComment.do")
public class BoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Thread t=new Thread(()->{
			try {
				Thread.sleep(3000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		});
		t.run();
		
		
		int boardNo=Integer.parseInt(request.getParameter("boardRef"));
		int level=Integer.parseInt(request.getParameter("level"));
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		int boardCommentRef=Integer.parseInt(request.getParameter("boardCommentRef"));
		
		BoardComment bc=BoardComment.builder()
				.boardCommentWriter(writer)
				.boardRef(boardNo)
				.boardCommentRef(boardCommentRef)
				.level(level)
				.boardCommentContent(content)
				.build();

		int result=new BoardService().insertBoardComment(bc);
		
		
		
		response.sendRedirect(request.getContextPath()+"/board/boardView.do?no="+boardNo);
		
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
