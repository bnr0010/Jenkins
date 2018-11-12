package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbursementImplementation;
import com.revature.model.Reimbursement;

public class ReimbursementService {
	private static ReimbursementService reimbursementService;
	
	private ReimbursementService() {
		
	}
	
	public static ReimbursementService getReimbursementService() {
		if(reimbursementService == null) {
			reimbursementService = new ReimbursementService();
		}
		return reimbursementService;
	}
	
	public static List<Reimbursement> getUserReimbursement(int userId) {
		return ReimbursementImplementation.getUserDao().getReinbursements(userId);
	}
	
	public static boolean newReimbursement(Reimbursement reimbursement) {
		return ReimbursementImplementation.getUserDao().createReimbursement(reimbursement);
	}

	public static List<Reimbursement> getPendingReimbursements() {
		return ReimbursementImplementation.getUserDao().getReinbursementsToApprove();
	}
	
	public static boolean approveEmployeeReimbursement(Reimbursement reimbursement) {
		return ReimbursementImplementation.getUserDao().approveReinbursement(reimbursement);
	}
	
	public static boolean denyEmployeeReimbursement(Reimbursement reimbursement) {
		return ReimbursementImplementation.getUserDao().denyReinbursement(reimbursement);
	}
	
	public static List<Reimbursement> getAllReimbs(){
		return ReimbursementImplementation.getUserDao().getAllReimbursements();
	}
}
