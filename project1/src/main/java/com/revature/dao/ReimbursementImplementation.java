package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Reimbursement;
import com.revature.util.JDBCConnectionUtil;

public class ReimbursementImplementation implements ReimbursementDao {
	
	private static ReimbursementImplementation reimbursementDao;
	
	final static Logger Log = Logger.getLogger(UserImplementation.class);
	
	private ReimbursementImplementation() {
		
	}
	
	public static ReimbursementImplementation getUserDao() {
		if(reimbursementDao == null) {
			reimbursementDao = new ReimbursementImplementation();
		}
		return reimbursementDao;
	}

	@Override
	public List<Reimbursement> getReinbursements(int userId) {
		try(Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "select * from reimbursement where user_id = " + userId;
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			List<Reimbursement> reimbursementList = new ArrayList<>();
			
			while(rs.next()) {
				Log.info("getAccounts executeQuery successful");
				reimbursementList.add(new Reimbursement(rs.getInt("user_id"),
						rs.getInt("amount"),
						rs.getString("type"),
						rs.getString("status"),
						rs.getString("date_submitted")));
			}
			
			return reimbursementList;
			
		} catch (SQLException s) {
			Log.error("catch block in getAccounts");
		} finally {
			Log.warn("executed finally block in getAccounts");
		}
		
		return new ArrayList<>();
	}

	@Override
	public boolean createReimbursement(Reimbursement reimbursement) {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			String storeProcs = "call insert_reimbursement(?, ?, ?, ?, ?)";
			CallableStatement cs = conn.prepareCall(storeProcs);
			
			cs.setInt(1, reimbursement.getUserId());
			cs.setInt(2, reimbursement.getAmount());
			cs.setString(3, reimbursement.getType());
			cs.setString(4, reimbursement.getStatus());
			cs.setString(5, reimbursement.getDateSubmitted());
			
			if(cs.executeUpdate() > -1) {
				Log.info("insertReimbursement executeUpdate successful");
				return true;
			}
			
		} catch (SQLException s) {
			Log.error("catch block in insertReimbursement occured");
			s.getMessage();
		} finally {
			Log.warn("executed finally block in insertReimbursement");
		}
		return false;
	}

	@Override
	public List<Reimbursement> getReinbursementsToApprove() {
		try(Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "select * from reimbursement where status = 'Pending'";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			List<Reimbursement> reimbursementList = new ArrayList<>();
			
			while(rs.next()) {
				Log.info("getUsersToValidate executeQuery successful");
				reimbursementList.add(new Reimbursement(rs.getInt("user_id"), 
						rs.getInt("amount"), 
						rs.getString("type"), 
						rs.getString("status"),
						rs.getString("date_submitted")));
			}
			return reimbursementList;
		
			
		} catch (SQLException s) {
			Log.error("catch block in getUsersToValidate");
		} finally {
			Log.warn("executed finally block in getUsersToValidate");
		}
		
		return new ArrayList<>();
	}

	@Override
	public boolean approveReinbursement(Reimbursement reimbursement) {
		try(Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "update reimbursement set status = 'Approved' where user_id = " + reimbursement.getUserId() +
					" and amount = " + reimbursement.getAmount() + " and date_submitted = '" + reimbursement.getDateSubmitted() + "'";
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
	
	public boolean denyReinbursement(Reimbursement reimbursement) {
		try(Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "update reimbursement set status = 'Denied' where user_id = " + reimbursement.getUserId() +
					" and amount = " + reimbursement.getAmount() + " and date_submitted = '" + reimbursement.getDateSubmitted() + "'";
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
	
	public List<Reimbursement> getAllReimbursements() {
		try(Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "select * from reimbursement";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			List<Reimbursement> reimbursementList = new ArrayList<>();
			
			while(rs.next()) {
				Log.info("getUsersToValidate executeQuery successful");
				reimbursementList.add(new Reimbursement(rs.getInt("user_id"), 
						rs.getInt("amount"), 
						rs.getString("type"), 
						rs.getString("status"),
						rs.getString("date_submitted")));
			}
			return reimbursementList;
		
			
		} catch (SQLException s) {
			Log.error("catch block in getUsersToValidate");
		} finally {
			Log.warn("executed finally block in getUsersToValidate");
		}
		
		return new ArrayList<>();
	}
}
