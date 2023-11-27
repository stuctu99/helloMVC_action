package com.web.member.model.service;

import static com.web.common.JDBCTemplate.close;
import static com.web.common.JDBCTemplate.getConnection;
import static com.web.common.JDBCTemplate.commit;
import static com.web.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.web.member.model.dao.MemberDao;
import com.web.member.model.dto.Member;
public class MemberService {
	
	private MemberDao dao=new MemberDao();
	
	public Member selectMemberByIdAndPw(String id,String password) {
		Connection conn=getConnection();
		Member m=dao.selectMemberByIdAndPw(conn,id,password);
		close(conn);
		return m;
	}
	
	public int insertMember(Member m) {
		Connection conn=getConnection();
		int result=dao.insertMember(conn,m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public Member selectMemberById(String userId) {
		Connection conn=getConnection();
		Member m=dao.selectMemberById(conn,userId);
		close(conn);
		return m;
	}
	
	
	public int updatePassword(String userId, String password) {
		Connection conn=getConnection();
		int result=dao.updatePassword(conn,userId,password);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	
}
