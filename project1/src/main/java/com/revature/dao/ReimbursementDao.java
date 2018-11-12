package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementDao {
	public List<Reimbursement> getReinbursements(int userId);
	public boolean createReimbursement(Reimbursement reimbursement);
	public List<Reimbursement> getReinbursementsToApprove();
	public boolean approveReinbursement(Reimbursement reinbursement);
	public List<Reimbursement> getAllReimbursements();
	public boolean denyReinbursement(Reimbursement reimbursement);
}
