package com.web.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	public static Connection getConnection() {
		Connection conn=null;
		Properties driver=new Properties();
		String path=JDBCTemplate.class.getResource("/driver.properties").getPath();
		try(FileReader fr=new FileReader(path);) {
			driver.load(fr);
			Class.forName(driver.getProperty("driver"));
			conn=DriverManager.getConnection(driver.getProperty("url"),
					driver.getProperty("user"),
					driver.getProperty("pw"));
			conn.setAutoCommit(false);
		}catch(Exception e) {
			e.printStackTrace();
		}return conn;
	}
	public static void close(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(Statement conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.commit();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.rollback();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
//	public static void close(Object conn) {
//		try {
//			if(conn!=null) {
//				if(conn instanceof Connection) {
//					((Connection)conn).close();
//				}else if(conn instanceof Statement) {
//					((Statement)conn).close();
//				}else if(conn instanceof PreparedStatement) {
//					((PreparedStatement)conn).close();
//				}else if(conn instanceof ResultSet) {
//					((ResultSet)conn).close();
//				}
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}	
//	}
	
}
