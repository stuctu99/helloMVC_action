package com.web.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.web.board.model.dto.Board;
import com.web.board.model.service.BoardService;
import com.web.common.exception.BadAccessException;

/**
 * Servlet implementation class BoardWriteEndServlet
 */
@WebServlet("/board/boardWriteEnd.do")
public class BoardWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			throw new BadAccessException("잘못된 접근입니다. 관리자에게 문의하세요!");
		}else {
			String path=getServletContext().getRealPath("/upload/");
			path+="board";
			File dir=new File(path);
			if(!dir.exists()) dir.mkdirs();
			MultipartRequest mr=new MultipartRequest(
					request, path,1024*1024*100,"UTF-8",
					new DefaultFileRenamePolicy());
			
			Board b=Board.builder()
					.boardTitle(mr.getParameter("title"))
					.boardWriter(mr.getParameter("writer"))
					.boardContent(mr.getParameter("content"))
					.boardOriginalFilename(mr.getOriginalFileName("upfile"))
					.boardRenamedFilename(mr.getFilesystemName("upfile"))
					.build();
			int result=new BoardService().insertBoard(b);
			String msg,loc;
			if(result>0) {
				msg="게시글 등록성공";
				loc="/board/boardList.do";
			}else {
				msg="게시글 등록실패";
				loc="/board/boardWrite.do";
				File delFile=new File(path+b.getBoardRenamedFilename());
				if(delFile.exists()) delFile.delete();
			}
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			request.getRequestDispatcher("/views/common/msg.jsp")
			.forward(request, response);
			
		}
	
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
