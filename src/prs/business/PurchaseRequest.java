package prs.business;

import java.time.LocalDate;
import java.time.LocalDateTime;

import prs.utility.StringUtils;

public class PurchaseRequest {
	private int id;
	private int userID;
	private String description;
	private String justification;
	private LocalDate dateNeeded;
	private String deliveryMode;
	private String status;
	private double total;
	private LocalDateTime submittedDate;
	private String reasonForRejection;
	
	public PurchaseRequest() {
		
	}

	public PurchaseRequest(int id, int userID, String description, String justification, LocalDate dateNeeded,
						   String deliveryMode, String status, double total, LocalDateTime submittedDate, String reasonForRejection) {
		this.id = id;
		this.userID = userID;
		this.description = description;
		this.justification = justification;
		this.dateNeeded = dateNeeded;
		this.deliveryMode = deliveryMode;
		this.status = status;
		this.total = total;
		this.submittedDate = submittedDate;
		this.reasonForRejection = reasonForRejection;
	}
	
	public PurchaseRequest(String description, String justification, LocalDate dateNeeded,
			   String deliveryMode, String status, double total, LocalDateTime submittedDate, String reasonForRejection) {
		this.description = description;
		this.justification = justification;
		this.dateNeeded = dateNeeded;
		this.deliveryMode = deliveryMode;
		this.status = status;
		this.total = total;
		this.submittedDate = submittedDate;
		this.reasonForRejection = reasonForRejection;
	}
	
	public PurchaseRequest(String description, String justification, LocalDate dateNeeded, User user,
			   String deliveryMode, String status, double total, LocalDateTime submittedDate, String reasonForRejection) {
		this.description = description;
		this.justification = justification;
		this.dateNeeded = dateNeeded;
		this.deliveryMode = deliveryMode;
		this.status = status;
		this.total = total;
		this.submittedDate = submittedDate;
		this.reasonForRejection = reasonForRejection;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public LocalDate getDateNeeded() {
		return dateNeeded;
	}

	public void setDateNeeded(LocalDate dateNeeded) {
		this.dateNeeded = dateNeeded;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public LocalDateTime getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(LocalDateTime submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getReasonForRejection() {
		return reasonForRejection;
	}

	public void setReasonForRejection(String reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}
	
	@Override
	public String toString() {
		return StringUtils.padWithSpaces("  "  + id, 10) + StringUtils.padWithSpaces(userID+"     ", 10) +
			   StringUtils.padWithSpaces(description, 35) + StringUtils.padWithSpaces(justification, 35) + 
			   StringUtils.padWithSpaces(dateNeeded+"", 15) + StringUtils.padWithSpaces(deliveryMode, 15) +
			   StringUtils.padWithSpaces(status, 20) + StringUtils.padWithSpaces(total+"", 15) +
			   StringUtils.padWithSpaces(submittedDate+"", 20) + StringUtils.padWithSpaces(reasonForRejection, 25) + "\n";
	}	
}
