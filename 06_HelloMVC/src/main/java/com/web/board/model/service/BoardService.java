package com.web.board.model.service;

import static com.web.common.JDBCTemplate.close;
import static com.web.common.JDBCTemplate.commit;
import static com.web.common.JDBCTemplate.getConnection;
import static com.web.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.web.board.model.dao.BoardDao;
import com.web.board.model.dto.Board;
import com.web.board.model.dto.BoardComment;
public class BoardService {
	private BoardDao dao=new BoardDao();
	
	public List<Board> selectBoard(int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Board> result=dao.selectBoard(conn, cPage, numPerpage);
		close(conn);
		return result;
	}
	public int selectBoardCount() {
		Connection conn=getConnection();
		int result=dao.selectBoardCount(conn);
		close(conn);
		return result;
	}
	public Board selectBoardByNo(int no,boolean readResult) {
		Connection conn=getConnection();
		Board b=dao.selectBoardByNo(conn, no);
		if(b!=null&&!readResult) {
			int result=dao.updateBoardReadCount(conn,no);
			
			if(result>0) { 
				commit(conn);
				b.setBoardReadcount(b.getBoardReadcount()+1);
			}
			else rollback(conn);
		}
		close(conn);
		return b;
	}
	public int insertBoard(Board b) {
		Connection conn=getConnection();
		int result=dao.insertBoard(conn, b);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int insertBoardComment(BoardComment bc) {
		Connection conn=getConnection();
		int result=dao.insertBoardComment(conn,bc);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public List<BoardComment> selectBoardComment(int no){
		Connection conn=getConnection();
		List<BoardComment> list=dao.selectBoardComment(conn,no);
		close(conn);
		return list;
	}
	
}






