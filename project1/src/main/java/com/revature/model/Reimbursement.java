package com.revature.model;

import java.io.Serializable;

public class Reimbursement implements Serializable {
	
	private static final long serialVersionUID = -1240034013181651173L;
	private int userId;
	private int amount;
	private String type;
	private String status;
	private String dateSubmitted;
	
	public Reimbursement() {
		super();
	}
	
	public Reimbursement(int userId, int amount, String type, String status, String dateSubmitted) {
		super();
		this.userId = userId;
		this.amount = amount;
		this.type = type;
		this.status = status;
		this.dateSubmitted = dateSubmitted;
	}

	@Override
	public String toString() {
		return "Reinbursement [userId=" + userId + ", amount=" + amount + ", type=" + type + ", status=" + status
				+ ", dateSubmitted=" + dateSubmitted + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((dateSubmitted == null) ? 0 : dateSubmitted.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (amount != other.amount)
			return false;
		if (dateSubmitted == null) {
			if (other.dateSubmitted != null)
				return false;
		} else if (!dateSubmitted.equals(other.dateSubmitted))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(String dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

}
