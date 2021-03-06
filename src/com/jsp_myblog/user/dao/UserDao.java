package com.jsp_myblog.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.jsp_myblog.user.command.UserSignupCommand;
import com.jsp_myblog.util.JdbcUtil;

public class UserDao {
	
	private String connectionUrl = "jdbc:mysql://b075e7f6a12e97:d108dc28@us-cdbr-east-03.cleardb.com/heroku_bf93596590ef5a3?reconnect=true";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	static final Logger logger = Logger.getLogger(UserSignupCommand.class);
	
	private final String USER_CHECK = "select * from users where id = ?";
	private final String USER_SIGNUP = "insert into users(id, password, name, email, secretKey) values(?, ?, ?, ?, ?)";
	private final String USER_LOGIN = "select name from users where id = ? and password = ?";
	private final String SECRET_KEY = "select secretKey from users where id = ?";
	
	public UserDao() {}
	
	public String getSecretKey(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connectionUrl, "b075e7f6a12e97", "d108dc28");
			String query = SECRET_KEY;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString("secretKey");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeResource(rs, pstmt, conn);
		}
		return null;
	}
	
	private boolean checkUser(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connectionUrl, "b075e7f6a12e97", "d108dc28");
			String query = USER_CHECK;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeResource(rs, pstmt, conn);
		}
		return true;
	}
	
	public void signup(String id, String password, String name, String email, String secretKey) {
		try {
			if(checkUser(id)) {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(connectionUrl, "b075e7f6a12e97", "d108dc28");
				String query = USER_SIGNUP;
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, id);
				pstmt.setString(2, password);
				pstmt.setString(3, name);
				pstmt.setString(4, email);
				pstmt.setString(5, secretKey);
				pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeResource(pstmt, conn);
		}
	}
	
	
	public String login(String id, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connectionUrl, "b075e7f6a12e97", "d108dc28");
			String query = USER_LOGIN;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString("name");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeResource(rs, pstmt, conn);
		}
		return null;
	}
}

