package com.web.notice.controller;

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
import com.web.common.exception.BadAccessException;
import com.web.notice.model.dto.Notice;
import com.web.notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeWriteEndServlet
 */
@WebServlet("/notice/noticewriteend.do")
public class NoticeWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지사항을 등록하기
		//1. 클라이언트가 보낸 데이터 저장
		// 파일저장하기
		//전송된 파일은 서버의 지정경로에 저장하고 DB에는 파일명만 저장함
		// 파일업로드처리를 하기 위해 cos.jar라이브러리를 이용
		// cos.jar가 제공하는 MultipartRequest클래스를 이용
		//MultipartRequest클래스는 매개변수있는 생성자로 생성해서 이용
		//1 : HttpServletRequest를 대입
		//2 : 파일을 저장할 위치 대입 -> 절대경로 -> String
		//3 : 업로드파일의 최대크기를 설정 -> byte단위 1024*1024*10 -> int
		//  byte -> KBYTE(1024) -> Mbyte(1024) -> Gbyte(1024) -> Tbyte
		//4 : 인코딩방식 : String * UTF-8
		//5 :  파일 rename규칙 설정(클래스) -> DefaultFileRenamePolicy클래스 제공
		
		
		//multipart/form-data방식으로 요청되었는지 확인
		if(!ServletFileUpload.isMultipartContent(request)) {
			throw new BadAccessException("잘못된 접근입니다. 관리자에게 문의하세요 :(");
		}else {
			//1. 파일을 저장할 위치를 절대경로로 가져오기
			//ServletContext클래스에서 getRealPath("/") -> webapp폴더
			String path=getServletContext().getRealPath("/upload/notice/");
			System.out.println(path);
			//2. 최대파일크기 지정
			int maxSize=1024*1024*100;//100MB
			//3. 인코딩설정
			String encoding="UTF-8";
			//4. rename규칙
			DefaultFileRenamePolicy dfr=new DefaultFileRenamePolicy();
			
			//매개변수값을 이용해서 MultipartRequest객체 생성하기
			MultipartRequest mr=new MultipartRequest(request,path,maxSize,encoding,dfr);
			
			
			//DB에 해당 내용을 저장
			String title=mr.getParameter("title");
			String writer=mr.getParameter("writer");
			String content=mr.getParameter("content");
			System.out.println(title+writer+content);
			//업로드된 파일명
			//MultipartRequest클래스가 제공하는 메소드를 이용해서 가져온다
			//getOriginalFileName("key");
			//getFilesystemName("key");
			String ori=mr.getOriginalFileName("upfile");
			String rename=mr.getFilesystemName("upfile");
			System.out.println(ori+" : "+rename);
			Notice n=Notice.builder()
					.noticeTitle(title)
					.noticeWriter(writer)
					.noticeContent(content)
					.filePath(rename)
					.build();
			
			int result=new NoticeService().insertNotice(n);
			String msg,loc;
			if(result>0) {
				//입력성공
//				request.getRequestDispatcher("/notice/noticeList.do")
//				.forward(request, response);
				msg="공지사항 등록성공";
				loc="/notice/noticeList.do";
			}else {
				//입력실패
				msg="공지사항 등록실패";
				loc="/notice/noticewrite.do";
				
				File delFile=new File(path+"/"+rename);
				if(delFile.exists()) delFile.delete();
			}
			request.setAttribute("msg", msg);
			request.setAttribute("loc",loc);
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
