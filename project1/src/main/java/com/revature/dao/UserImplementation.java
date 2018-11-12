package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.util.JDBCConnectionUtil;

public class UserImplementation implements UserDao {

	private static UserImplementation userDao;
	
	final static Logger Log = Logger.getLogger(UserImplementation.class);
	
	private UserImplementation() {
		
	}
	
	public static UserImplementation getUserDao() {
		if(userDao == null) {
			userDao = new UserImplementation();
		}
		return userDao;
	}

	@Override
	public boolean insertUserProcedure(User user) {
		try(Connection conn = JDBCConnectionUtil.getConnection()){
			String storeProcs = "call insert_user(?, ?, ?, ?, ?, ?)";
			CallableStatement cs = conn.prepareCall(storeProcs);
			
			cs.setString(1, user.getFirstName());
			cs.setString(2, user.getLastName());
			cs.setString(3, user.getUsername());
			cs.setString(4, user.getPassword());
			cs.setString(5, user.getEmail());
			cs.setInt(6, user.getIsManager());
			
			if(cs.executeUpdate() > 0) {
				Log.info("insertUserProcedure executeUpdate successful");
				return true;
			}
			
		} catch (SQLException s) {
			Log.error("catch block in insertUserProcedure occured");
			s.getMessage();
		} finally {
			Log.warn("execute finally block in insertUserProcedure");
		}
		return false;
	}

	@Override
	public User getUser(int userId) {
		try(Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "select * from user_table where user_id = " + userId;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Log.info("getUser executeQuery successful");
				return new User(rs.getInt("user_id"), 
						rs.getString("first_name"), 
						rs.getString("last_name"), 
						rs.getString("username"), 
						rs.getString("password"),
						rs.getString("email"),
						rs.getInt("is_manager"));
			}
			
		} catch (SQLException s) {
			Log.error("catch block in getUser");
		} finally {
			Log.warn("executed finally block in getUser");
		}
		
		return new User();
	}

	@Override
	public List<User> getAllUsers() {
		try(Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "select * from user_table";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			List<User> userList = new ArrayList<>();
			
			while(rs.next()) {
				Log.info("getUser executeQuery successful");
				userList.add(new User(rs.getInt("user_id"), 
						rs.getString("first_name"), 
						rs.getString("last_name"), 
						rs.getString("username"), 
						rs.getString("password"),
						rs.getString("email"),
						rs.getInt("is_manager")));
			}
			return userList;
			
		} catch (SQLException s) {
			Log.error("catch block in getUser");
		} finally {
			Log.warn("executed finally block in getUser");
		}
		
		return new ArrayList<>();
	}

	@Override
	public User logInUser(String username) {
		try(Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "select * from USER_TABLE where username = '" + username + "'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Log.info("logInUser executeQuery successful");
				return new User(rs.getInt("user_id"), 
						rs.getString("first_name"), 
						rs.getString("last_name"), 
						rs.getString("username"), 
						rs.getString("password"),
						rs.getString("email"),
						rs.getInt("is_manager"));
			}
			
		} catch (SQLException s) {
			Log.error("catch block in logInUser");
			s.printStackTrace();
		} finally {
			Log.warn("executed finally block in logInUser");
		}
		
		return new User();
	}
	
	public boolean updateUser(User user) {
		try(Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "update user_table set username = '" + user.getUsername() +
					"', password = '" + user.getPassword() +
					"', email = '" + user.getEmail() + "' where user_id = " + user.getUserId();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.rowUpdated()) {
				Log.info("validateUser executeQuery successful");
				return true;
			}
			
		} catch (SQLException s) {
			Log.error("catch block in validateUser");
		} finally {
			Log.warn("executed finally block in validateUser");
		}
		
		return false;
	}
}
