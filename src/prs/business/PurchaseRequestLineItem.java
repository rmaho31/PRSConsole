package prs.business;

import prs.utility.StringUtils;

public class PurchaseRequestLineItem {
	private int id;
	private int purchaseRequestID;
	private int productID;
	private int quantity;
	
	public PurchaseRequestLineItem() {
		
	}
	
	public PurchaseRequestLineItem(int id, int purchaseRequestID, int productID, int quantity) {
		this.id = id;
		this.purchaseRequestID = purchaseRequestID;
		this.productID = productID;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPurchaseRequestID() {
		return purchaseRequestID;
	}

	public void setPurchaseRequestID(int purchaseRequestID) {
		this.purchaseRequestID = purchaseRequestID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return StringUtils.padWithSpaces("  "  + id, 10) + StringUtils.padWithSpaces(purchaseRequestID+"     ", 15) +
			   StringUtils.padWithSpaces(productID+"", 15) + StringUtils.padWithSpaces(quantity+"", 15) + "\n";
	}
}
