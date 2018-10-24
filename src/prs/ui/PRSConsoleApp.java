package prs.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import prs.business.*;
import prs.utility.Console;
import prs.utility.StringUtils;

public class PRSConsoleApp {
	private static ProductDB pdb = new ProductDB();
	private static UserDB udb = new UserDB();
	private static PurchaseRequestDB prdb = new PurchaseRequestDB();
	private static PurchaseRequestLineItemDB prlidb = new PurchaseRequestLineItemDB();
	private static VendorDB vdb = new VendorDB();
	private static User user = null;

	public static void main(String[] args) {
		System.out.println("Purchase Request System");
		boolean isRunning = true;
		while(isRunning) {
			int command = 0;
			boolean isLoggedIn = false;
			displayLoginMenu();
			command = Console.getInt("Enter Command: ", 0, 3);
			if(command == 1) {
				isLoggedIn = loginCheckUpdateUser(Console.getString("Enter UserName: "), Console.getString("Enter Password: "));					
			} else {
				isRunning = false;
			}
			while(isLoggedIn) {
				while(command != 6) {
					displayMainMenu();
					command = Console.getInt("Enter Command: ", 0, 7);
					subMenuLoop(command); 
				}						
			}
		}
		System.out.println("\nBye!");
	}

	private static void subMenuLoop(int command) {	
		if (command == 1) {
			entityCRUDFunctions(command);
		} else if(command == 2) {
			entityCRUDFunctions(command);
		} else if (command == 3) {
			entityCRUDFunctions(command);
		} else if (command == 4) {
			entityCRUDFunctions(command);
		} else if (command == 5) {
			entityCRUDFunctions(command);
		}
	}

	private static void entityCRUDFunctions(int command) {
		int subCommand = 0;
		while(subCommand != 5) {
			displayEntityMenu(command);
			subCommand = Console.getInt("Enter Command: ", 0, 6);
			if (subCommand == 1) {
				displayObjects(getObjects(command),command);
			} else if(subCommand == 2) {
				insertToDB(command);
			} else if(subCommand == 3) {
				displayObjects(getObjects(command),command);
				updateDB(command);
			} else if(subCommand == 4) {
				displayObjects(getObjects(command),command);
				deleteRowbyID(command);
			}
		}
	}
		
	private static boolean loginCheckUpdateUser(String string, String string2) {
		List<Object> users = udb.getAll();
		for(Object o : users) {
			User u = (User) o;
			if(u.getUserName().equals(string) && u.getPassword().equals(string2)) {
				user = u;
				return true;
			}
		}
		return false;
	}

	private static void displayMainMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nMAIN MENU\n");
		sb.append("1 - View User Menu\n");
		sb.append("2 - View Vendor Menu\n");
		sb.append("3 - View Product Menu\n");
		sb.append("4 - View Purchase Request Menu\n");
		sb.append("5 - View Line Items Menu\n");
		sb.append("6 - Exit program\n");
		System.out.println(sb);
	}
	private static void displayEntityMenu(int command) {
		StringBuilder sb = new StringBuilder();
		if(command == 1) {
			sb.append("\nUSER MENU\n");
			sb.append("1 - View Users\n");
			sb.append("2 - Add User\n");
			sb.append("3 - Update User\n");
			sb.append("4 - Delete User\n");
			sb.append("5 - Go Back\n");
		} else if(command == 2) {
			sb.append("\nVENDOR MENU\n");
			sb.append("1 - View Vendors\n");
			sb.append("2 - Add Vendor\n");
			sb.append("3 - Update Vendor\n");
			sb.append("4 - Delete Vendor\n");
			sb.append("5 - Go Back\n");
		} else if(command == 3) {
			sb.append("\nPRODUCT MENU\n");
			sb.append("1 - View Products\n");
			sb.append("2 - Add Product\n");
			sb.append("3 - Update Product\n");
			sb.append("4 - Delete Product\n");
			sb.append("5 - Go Back\n");
		} else if(command == 4) {
			sb.append("\nPURCHASE REQUEST MENU\n");
			sb.append("1 - View Purchase Requests\n");
			sb.append("2 - Add Purchase Request\n");
			sb.append("3 - Update Purchase Request\n");
			sb.append("4 - Delete Purchase Request\n");
			sb.append("5 - Go Back\n");		
		} else if(command == 5) {
			sb.append("\nLINE ITEM MENU\n");
			sb.append("1 - View Line Items\n");
			sb.append("2 - Add Line Item\n");
			sb.append("3 - Update Line Item\n");
			sb.append("4 - Delete Line Item\n");
			sb.append("5 - Go Back\n");			
		}
		System.out.println(sb);
	}
	
	private static void displayLoginMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nPRS LOGIN\n");
		sb.append("1 - Login\n");
		sb.append("2 - Exit\n");
		System.out.println(sb);
	}
	
	private static void displayObjects(String s, int command) {
		if(command == 1) {
			String header = StringUtils.padWithEquals("Users", 170) + "\n" +
					StringUtils.padWithUnderscores("ID#", 10) + StringUtils.padWithUnderscores("UserName", 20) +
					StringUtils.padWithUnderscores("Password", 20) + StringUtils.padWithUnderscores("First Name", 20) + 
					StringUtils.padWithUnderscores("Last Name", 20) + StringUtils.padWithUnderscores("Phone Number", 15) +
					StringUtils.padWithUnderscores("Email", 35) + StringUtils.padWithUnderscores("Reviewer", 15) +
					StringUtils.padWithUnderscores("Admin", 15) + "\n";
			Console.println(header + s);	
		} else if(command == 2) {
			String header = StringUtils.padWithEquals("Vendors", 170) + "\n" +
				    StringUtils.padWithUnderscores("ID#", 10) + StringUtils.padWithUnderscores("Code", 10) +
				    StringUtils.padWithUnderscores("Name", 20) + StringUtils.padWithUnderscores("Address", 25) + 
				    StringUtils.padWithUnderscores("City", 15) + StringUtils.padWithUnderscores("State", 15) + 
				    StringUtils.padWithUnderscores("Zip", 10) + StringUtils.padWithUnderscores("Phone Number", 15) + 
				    StringUtils.padWithUnderscores("Email", 35) + StringUtils.padWithUnderscores("Pre Approved", 15) + "\n";
			Console.println(header + s);	
		} else if(command == 3) {
			String header = StringUtils.padWithEquals("Products", 140) + "\n" +
				    StringUtils.padWithUnderscores("ID#", 5) + StringUtils.padWithUnderscores("VendorID#", 15) +
				    StringUtils.padWithUnderscores("PartNumber", 20) + StringUtils.padWithUnderscores("Name", 25) + 
				    StringUtils.padWithUnderscores("Price", 15) + StringUtils.padWithUnderscores("Unit", 25) + 
				    StringUtils.padWithUnderscores("Photo Path", 35) + "\n";
			Console.println(header + s);	
		} else if(command == 4) {
			String header = StringUtils.padWithEquals("Purchase Requests", 200) + "\n" +
				    StringUtils.padWithUnderscores("ID#", 10) + StringUtils.padWithUnderscores("UserID#", 10) +
				    StringUtils.padWithUnderscores("Description", 35) + StringUtils.padWithUnderscores("Justification", 35) + 
				    StringUtils.padWithUnderscores("Date Needed", 15) + StringUtils.padWithUnderscores("Delivery Mode", 15) + 
				    StringUtils.padWithUnderscores("Status", 20) + StringUtils.padWithUnderscores("Total", 15) + 
				    StringUtils.padWithUnderscores("Submitted Date", 20) + StringUtils.padWithUnderscores("Reason For Rejection", 25) + "\n";
			Console.println(header + s);	
		} else {
			String header = StringUtils.padWithEquals("Line Item", 50) + "\n" +
				    StringUtils.padWithUnderscores("ID#", 10) + StringUtils.padWithUnderscores("PurchaseRequestID#", 10) +
				    StringUtils.padWithUnderscores("ProductID#", 15) + StringUtils.padWithUnderscores("Quantity", 15) + "\n";
			Console.println(header + s);
		}
	}
	
	private static void updateDB(int command) {
		if(command == 1) {
			int subCommand = 0;
			User u = (User) getObjectByID(Console.getInt("Enter User id number: "), command);
			while(subCommand != 9) {
				Console.println("Select Field to change\n" + "\n1. Edit UserName " + "\t\t2. Edit Password " +
								"\t\t3. Edit First Name" + "\n4. Edit Last Name" + "\t\t5. Edit Phone Number " + "\t\t6. Edit Email" +
								"\n7. Edit IsReviewer" +  "\t\t8. Edit IsAdmin" + "\t\t9. Return to menu\n");
				subCommand = Console.getInt("Command: ", 0, 10);
				if(subCommand == 1) {
					displayObject(u, command);
					u.setUserName(Console.getString("Enter User Name: "));
					udb.update(u);
				} else if(subCommand == 2) {
					displayObject(u, command);
					u.setPassword(Console.getString("Enter Password: "));
					udb.update(u);
				} else if(subCommand == 3) {
					displayObject(u, command);
					u.setFirstName(Console.getString("Enter First Name: "));
					udb.update(u);
				} else if(subCommand == 4) {
					displayObject(u, command);
					u.setLastName(Console.getString("Enter Last Name: "));
					udb.update(u);
				} else if(subCommand == 5) {
					displayObject(u, command);
					u.setPhoneNumber(Console.getString("Enter Phone Number: "));
					udb.update(u);
				} else if(subCommand == 6) {
					displayObject(u, command);
					u.setEmail(Console.getString("Enter Email: "));
					udb.update(u);
				} else if(subCommand == 7) {
					displayObject(u, command);
					u.setReviewer(Console.getBoolean("Enter IsReviewer (true/false): "));
					udb.update(u);
				} else if(subCommand == 8) {
					displayObject(u, command);
					u.setAdmin(Console.getBoolean("Enter IsAdmin (true/false): "));
					udb.update(u);
				}
			}			
		} else if (command == 2) {
			int subCommand = 0;
			Vendor vendor = (Vendor) getObjectByID(Console.getInt("Enter Vendor id number: "), command);
			while(subCommand != 10) {
				Console.println("Select Field to change\n" + 
								"\n1. Edit Code " + "\t\t2. Edit Name " + "\t\t3. Edit Address" +
								"\n4. Edit City" + "\t\t5. Edit State " + "\t\t6. Edit Zip" +
								"\n7. Edit Phone Number" +  "\t\t8. Edit Email" + "\t\t9. Is Pre Approved" +
								"\n10. Return to menu\n");
				subCommand = Console.getInt("Command: ", 0, 11);
				if(subCommand == 1) {
					displayObject(vendor, command);
					vendor.setCode(Console.getString("Enter Code: "));
					vdb.update(vendor);
				} else if(subCommand == 2) {
					displayObject(vendor, command);
					vendor.setName(Console.getString("Enter Name: "));
					vdb.update(vendor);
				} else if(subCommand == 3) {
					displayObject(vendor, command);
					vendor.setAddress(Console.getString("Enter Address: "));
					vdb.update(vendor);
				} else if(subCommand == 4) {
					displayObject(vendor, command);
					vendor.setCity(Console.getString("Enter City: "));
					vdb.update(vendor);
				} else if(subCommand == 5) {
					displayObject(vendor, command);
					vendor.setState(Console.getString("Enter State: "));
					vdb.update(vendor);
				} else if(subCommand == 6) {
					displayObject(vendor, command);
					vendor.setZip(Console.getString("Enter Zip: "));
					vdb.update(vendor);
				} else if(subCommand == 7) {
					displayObject(vendor, command);
					vendor.setPhoneNumber(Console.getString("Enter Phone Number: "));
					vdb.update(vendor);
				} else if(subCommand == 8) {
					displayObject(vendor, command);
					vendor.setEmail(Console.getString("Enter Email: "));
					vdb.update(vendor);
				} else if(subCommand == 9) {
					displayObject(vendor, command);
					vendor.setPreApproved(Console.getBoolean("Enter Is Pre Approved (true/false): "));
					vdb.update(vendor);
				}
			}
		} else if (command == 3) {
			int subCommand = 0;
			Product product = (Product) getObjectByID(Console.getInt("Enter Product number: "), command);
			while(subCommand != 7) {
				Console.println("Select Field to change\n" + 
								"\n1. Edit VendorID " + "\t\t2. Edit Part Number " + "\t\t3. Edit Name" +
								"\n4. Edit Price" + "\t\t5. Edit Unit " + "\t\t6. Edit Photo Path" +
								"\n7. Return to menu\n");
				subCommand = Console.getInt("Command: ", 0, 8);
				if(subCommand == 1) {
					displayObject(product, command);
					product.setVendorID(Console.getInt("Enter VendorID: "));
					pdb.update(product);
				} else if(subCommand == 2) {
					displayObject(product, command);
					product.setPartNumber(Console.getString("Enter Part Number: "));
					pdb.update(product);
				} else if(subCommand == 3) {
					displayObject(product, command);
					product.setName(Console.getString("Enter Name: "));
					pdb.update(product);
				} else if(subCommand == 4) {
					displayObject(product, command);
					product.setPrice(Console.getDouble("Enter Price: "));
					pdb.update(product);
				} else if(subCommand == 5) {
					displayObject(product, command);
					product.setUnit(Console.getString("Enter Unit: "));
					pdb.update(product);
				} else if(subCommand == 6) {
					displayObject(product, command);
					product.setPhotoPath(Console.getString("Enter Photo Path: "));
					pdb.update(product);
				}
			}	
		} else if (command == 4) {
			int subCommand = 0;
			PurchaseRequest pr = (PurchaseRequest) getObjectByID(Console.getInt("Enter Purchase Request id number: "), command);
			while(subCommand != 10) {
				Console.println("Select Field to change\n" + 
								"\n1. Edit UserID " + "\t\t2. Edit Description " + "\t\t3. Edit Justification" +
								"\n4. Edit DateNeeded" + "\t\t5. Edit Delivery Mode " + "\t\t6. Edit Status" +
								"\n7. Edit Total" +  "\t\t8. Edit Submitted Date" + "\t\t9. Reason For Rejection" +
								"\n10. Return to menu\n");
				subCommand = Console.getInt("Command: ", 0, 11);
				if(subCommand == 1) {
					displayObject(pr, command);
					pr.setUserID(Console.getInt("Enter UserID: "));
					prdb.update(pr, user);
				} else if(subCommand == 2) {
					displayObject(pr, command);
					pr.setDescription(Console.getString("Enter Description: "));
					prdb.update(pr, user);
				} else if(subCommand == 3) {
					displayObject(pr, command);
					pr.setJustification(Console.getString("Enter Justification: "));
					prdb.update(pr, user);
				} else if(subCommand == 4) {
					displayObject(pr, command);
					pr.setDateNeeded(
							LocalDate.parse(Console.getString("Enter Date Needed\n YYYY-MM-DD: ",
															  "\\d+-\\d+-\\d{2}")));
					prdb.update(pr, user);
				} else if(subCommand == 5) {
					displayObject(pr, command);
					pr.setDeliveryMode(Console.getString("Enter Delivery Mode: "));
					prdb.update(pr, user);
				} else if(subCommand == 6) {
					displayObject(pr, command);
					pr.setStatus(Console.getString("Enter Status: "));
					prdb.update(pr, user);
				} else if(subCommand == 7) {
					displayObject(pr, command);
					pr.setTotal(Console.getDouble("Enter Total: "));
					prdb.update(pr, user);
				} else if(subCommand == 8) {
					displayObject(pr, command);
					pr.setSubmittedDate(
							LocalDateTime.parse(Console.getString("Enter Submitted Date\n YYYY-MM-DD hh:mm:ss: ",
																  "\\d+-\\d+-\\d+\u0020\\d+:\\d+:\\d{2}").replace(" ", "T")));
					prdb.update(pr, user);
				} else if(subCommand == 9) {
					displayObject(pr, command);
					pr.setReasonForRejection(Console.getString("Enter Reason for Rejection: "));
					prdb.update(pr, user);
				}
			}
		} else {
			int subCommand = 0;
			PurchaseRequestLineItem prli = (PurchaseRequestLineItem) getObjectByID(Console.getInt("Enter Line Item id number: "), command);
			while(subCommand != 4) {
				Console.println("Select Field to change\n" + 
								"\n1. Edit Purchase Request ID " + "\t\t2. Edit ProductID " + "\t\t3. Edit Quantity" +
								"\n4. Return to menu\n");
				subCommand = Console.getInt("Command: ", 0, 10);
				if(subCommand == 1) {
					displayObject(prli, command);
					prli.setPurchaseRequestID(Console.getInt("Enter Purchase Request ID: "));
					prlidb.update(prli);
				} else if(subCommand == 2) {
					displayObject(prli, command);
					prli.setProductID(Console.getInt("Enter ProductID: "));
					prlidb.update(prli);
				} else if(subCommand == 3) {
					displayObject(prli, command);
					prli.setQuantity(Console.getInt("Enter Quantity: "));
					prlidb.update(prli);
				}
			}
		}		
	}
	
	private static void insertToDB(int command) {
		//allows the user to add entries to the db
		if(command == 1) {
			User user = new User(Console.getString("Enter UserName: "),
								 Console.getString("Enter Password: "),
								 Console.getString("Enter First Name: "),
								 Console.getString("Enter Last Name: "),
								 Console.getString("Enter Phone Number: "),
								 Console.getString("Enter Email: "),
								 Console.getBoolean("Enter IsReviewer (true/false): "),
								 Console.getBoolean("Enter IsAdmin (true/false): "));
			udb.add(user);
		} else if (command == 2) {
			Vendor vendor = new Vendor(Console.getString("Enter Code: "),
					 				   Console.getString("Enter Name: "),
					 				   Console.getString("Enter Address: "),
					 				   Console.getString("Enter City: "),
					 				   Console.getString("Enter State: "),
					 				   Console.getString("Enter Zip: "),
					 				   Console.getString("Enter Phone Number: "),
					 				   Console.getString("Enter Email: "),
					 				   Console.getBoolean("Enter IsPreApproved (true/false): "));
			vdb.add(vendor);
		} else if (command == 3) {
			displayObjects(getObjects(command),command);
			Product p = new Product(Console.getInt("Enter Vendor ID: "),
	 				   				Console.getString("Enter Part Number: "),
	 				   				Console.getString("Enter Name: "),
	 				   				Console.getDouble("Enter Price: "),
	 				   				Console.getString("Enter Unit: "),
	 				   				Console.getString("Enter Photo Path: "));
			pdb.add(p);
		} else if (command == 4) {
			PurchaseRequest pr = 
					new PurchaseRequest(Console.getString("Enter Description: "),
					 				    Console.getString("Enter Justification: "),
									    LocalDate.parse(Console.getString("Enter Date Needed\nYYYY-MM-DD: ",
									    								  "\\d+-\\d+-\\d{2}")),
										Console.getString("Enter Delivery Mode: "),
										Console.getString("Enter Status: "),
										Console.getDouble("Enter Total: "),
										LocalDateTime.now(),
										Console.getString("Enter Reason for Rejection: "));
			prdb.add(pr, user);
		} else {
			PurchaseRequestLineItem prli = 
					new PurchaseRequestLineItem(Console.getInt("Enter PurchaseRequestId: "),
												Console.getInt("Enter ProductID: "),
	 				   							Console.getInt("Enter Quantity: "));
			prlidb.add(prli);
		}
	}
	
	private static void deleteRowbyID(int command) {
		if(command == 1) {
			displayObjects(getObjects(command),command);
			User user = (User) getObjectByID(Console.getInt("Enter User id number: "), command);
			udb.delete(user);
		} else if (command == 2) {
			displayObjects(getObjects(command),command);
			Vendor vendor = (Vendor) getObjectByID(Console.getInt("Enter Vendor id number: "), command);
			vdb.delete(vendor);
		} else if (command == 3) {
			displayObjects(getObjects(command),command);
			Product product = (Product) getObjectByID(Console.getInt("Enter Product number: "), command);
			pdb.delete(product);	
		} else if (command == 4) {
			displayObjects(getObjects(command),command);
			PurchaseRequest pr = (PurchaseRequest) getObjectByID(Console.getInt("Enter Purchase Request id number: "), command);
			prdb.delete(pr);
		} else {
			displayObjects(getObjects(command),command);
			PurchaseRequestLineItem prli = (PurchaseRequestLineItem) getObjectByID(Console.getInt("Enter Line Item id number: "), command);
			prlidb.delete(prli);
		}
	}
	
	private static List<Object> getObjects(int c) {
		//allows user to get the objects from the db
		List<Object> objects = null;
		if (c == 1) {
			objects = udb.getAll();
		} else if(c == 2) {
			objects = vdb.getAll();
		} else if (c == 3) {
			objects = pdb.getAll();			
		} else if (c == 4) {
			objects = prdb.getAll();
		} else {
			objects = prlidb.getAll();
		}
		return objects;
	}
	
	private static void displayObjects(List<Object> objects, int c) {
		//allows user to view the objects in the db
		String row = "";
			if(objects.size() == 0) {
				Console.println("Add some entries to display them.");
			} else {
				for(Object o : objects) {
					row += o.toString();
				}
				displayObjects(row, c);					
			}
	}
	
	private static void displayObject(Object object, int c) {
		//allows user to view the objects in the db
		String row = "";
			if(object == null) {
				Console.println("No object found.");
			} else {
				row += object.toString();
				displayObjects(row, c);					
			}
	}
	
	private static Object getObjectByID(int n, int c) {
		if(c == 1) {
			Object user = udb.getbyID(n);
			if(user != null) {
				return user;				
			} else {
				Console.println("\nThere is no User with id " + n + "!\n");
				return getObjectByID(Console.getInt("Enter User id number: "), c);
			}			
		} else if (c == 2) {
			Object vendor = vdb.getbyID(n);
			if(vendor != null) {
				return vendor;				
			} else {
				Console.println("\nThere is no Vendor with id " + n + "!\n");
				return getObjectByID(Console.getInt("Enter Vendor id number: "), c);
			}
		} else if (c == 3) {
			Object product = pdb.getbyID(n);
			if(product != null) {
				return product;				
			} else {
				Console.println("\nThere is no Product with id " + n + "!\n");
				return getObjectByID(Console.getInt("Enter Product id number: "), c);
			}
		} else if (c == 4) {	
			Object pr = prdb.getbyID(n);
			if(pr != null) {
				return pr;				
			} else {
				Console.println("\nThere is no Purchase Request with id " + n + "!\n");
				return getObjectByID(Console.getInt("Enter Purchase Request id number: "), c);
			}
		} else {
			Object prli = prlidb.getbyID(n);
			if(prli != null) {
				return prli;				
			} else {
				Console.println("\nThere is no Line Item with id " + n + "!\n");
				return getObjectByID(Console.getInt("Enter Line Item id number: "), c);
			}
		}
	}
}
