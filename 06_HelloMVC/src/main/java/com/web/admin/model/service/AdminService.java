package com.web.admin.model.service;

import java.sql.Connection;
import java.util.List;

import com.web.admin.model.dao.AdminDao;
import com.web.member.model.dto.Member;
import static com.web.common.JDBCTemplate.*;

public class AdminService {
	
	private AdminDao dao=new AdminDao();
	
	public List<Member> searchMemberList(int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Member> result=dao.searchMemberList(conn,cPage,numPerpage);
		close(conn);
		return result;
	}
	public int selectMemberCount() {
		Connection conn=getConnection();
		int count=dao.selectMemberCount(conn);
		close(conn);
		return count;
	}
	
	public List<Member> searchMemberByKeyword(String type, String keyword,
			int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Member> result=dao.searchMemberByKeyword(conn,type,keyword,
				cPage,numPerpage);
		close(conn);
		return result;
	}
	
	public int selectMemberByKeywordCount(String type,String keyword) {
		Connection conn=getConnection();
		int result=dao.selectMemberByKeywordCount(conn,type,keyword);
		close(conn);
		return result;
	}
	
	
}
