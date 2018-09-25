package prs.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.OptionalInt;
import prs.business.*;
import prs.utility.Console;

public class PRSConsoleApp {
	private static ProductDB tdb = new ProductDB();

	public static void main(String[] args) {
			System.out.println("Welcome to the Product List App");
			int command = 0;
			while(command != 8) {
				displayMenu();
				command = Console.getInt("Enter Command: ", 0, 9);
				if (command == 1) {
					printProducts();
				} else if(command == 2) {
					insertToDB();
				} else if (command == 3) {
					printProductsUpdateCompleted();
				} else if (command == 4) {
					printProductsDeleteIndex();
				} else if (command == 5) {
					printProductsUpdateDB();
				} else if(command == 6) {
					printDaysToNext();
				}
			}		
			System.out.println("\nBye!");
		}
		
		private static void displayMenu() {
			StringBuilder sb = new StringBuilder();
			sb.append("\nCOMMAND MENU\n");
			sb.append("1 - View\t-View pending products\n");
			sb.append("2 - History\t-View completed products\n");
			sb.append("3 - Add\t\t-Add a product\n");
			sb.append("4 - Complete\t-Complete a product\n");
			sb.append("5 - Delete\t-Delete a product\n");
			sb.append("6 - Update\t-Update pending product\n");
			sb.append("7 - Days\t-Days until next product\n");
			sb.append("8 - Exit\t-Exit program\n");
			System.out.println(sb);
		}
		
		private static void displayProducts(String s) {
			String header = "========================================================= Pending Products ===============================================================\n" +
						    "Product#__VendorID#_____PartNumber__________Name__________________________________________________Price_______Unit__________photoPath_____\n";
			System.out.println(header + s);
		}
		
		private static void printProductsUpdateDB() {
			List<Product> product = tdb.get(false);
			if(product.size() == 0) {
				Console.println("Add some products to edit them.");
			} else {
				//allows user to update pending products in the db
				String row = "";
				for(Product p : product) {
					row += p.toString();
				}
				displayProducts(row);
				String choice = Console.getString("Edit product or date? (t/d): ", "t", "d");
				if(choice.equalsIgnoreCase("t")) {
					int index = 0;
					boolean isTrue = true;
					while(isTrue) {
						//again I'm sure this is a terrible workaround but avoids null pointers
						index = Console.getInt("Enter Product Number: ");
						final Integer indexw = new Integer(index);
						if(product.stream().anyMatch(n -> n.getId() == indexw)) {
							isTrue = false;
						} else {
							Console.println("Must enter a product number from the lisp.");
						}
					}
					
					Product p = tdb.getbyID(index);
					p.setName(Console.getString("Enter updated product: "));
					tdb.update(p);					
				} else {
					int index = 0;
					boolean isTrue = true;
					while(isTrue) {
						//again I'm sure this is a terrible workaround but avoids null pointers
						index = Console.getInt("Enter Product Number: ");
						final Integer indexw = new Integer(index);
						if(product.stream().anyMatch(n -> n.getId() == indexw)) {
							isTrue = false;
						} else {
							Console.println("Must enter a product number from the lisp.");
						}
					}
					isTrue = true;
					String dateTime = "";
					LocalDateTime dt = null;
					while(isTrue) {
						//ensures proper format of date entry
						try{
							dateTime = Console.getString("Enter updated Date and Time (YYYY-MM-DD HH:mm:ss): ").replace(" ", "T");
							if(dateTime.matches("\\d+-\\d+-\\d+T\\d+:\\d+:\\d{2}")) {
								dt = LocalDateTime.parse(dateTime);
								isTrue = false;
							} else {
								Console.println("Input DateTime in the correct format please.");
							}
						} catch (DateTimeParseException e) {
							Console.println("Must be of valid date format");
						}
					}
					Product p = tdb.getbyID(index);
					p.setName("");
					tdb.update(p);
				}	
			}
		}
		
		private static void insertToDB() {
			//allows the user to add entries to the db
			boolean isTrue = true;
			String dateTime = "";
			LocalDateTime dt = null;
			while(isTrue) {
				//ensures proper format of date entry
				try{
					dateTime = Console.getString("Enter updated Date and Time (YYYY-MM-DD HH:mm:ss): ").replace(" ", "T");
					if(dateTime.matches("\\d+-\\d+-\\d+T\\d+:\\d+:\\d{2}")) {
						dt = LocalDateTime.parse(dateTime);
						isTrue = false;
					} else {
						Console.println("Input DateTime in the correct format please.");
					}
				} catch (DateTimeParseException e) {
					Console.println("Must be of valid date format");
				}
			}
			String product = Console.getString("Enter Product: ");
			Product p = new Product();				  
			tdb.add(p);
		}
		
		private static void printProductsUpdateCompleted() {
			//allows users to set pending products as completed
			List<Product> product = tdb.get(false);
			if(product.size() == 0) {
				Console.println("Complete some products to delete them.");
			} else {
				String row = "";
				for(Product p : product) {
					row += p.toString();
				}
				displayProducts(row);
				int index = 0;
				boolean isTrue = true;
				while(isTrue) {
					//I'm sure this is a terrible workaround but avoids null pointers
					index = Console.getInt("Enter Product Number: ");
					final Integer indexw = new Integer(index);
					if(product.stream().anyMatch(n -> n.getId() == indexw)) {
						isTrue = false;
					} else {
						Console.println("Must enter a index number from the lisp.");
					}
				}
				Product p = tdb.getbyID(index);
				p.setName("");
				tdb.update(p);			
			}
		}
		
		private static void printProductsDeleteIndex() {
			List<Product> product = tdb.get(true);
			if(product.size() == 0) {
				Console.println("Complete some products to delete them.");
			} else {
				//allows users to delete completed records
				String row = "";
				for(Product p : product) {
					//replaces newline at the end of the toString to add the done
					row += p.toString().replaceAll("[\\n]", "") + "(DONE!)\n";
				}
				displayProducts(row);
				int index = 0;
				boolean isTrue = true;
				while(isTrue) {
					//again I'm sure this is a terrible workaround but avoids null pointers
					index = Console.getInt("Enter Product Number: ");
					final Integer indexw = new Integer(index);
					if(product.stream().anyMatch(n -> n.getId() == indexw)) {
						isTrue = false;
					} else {
						Console.println("Must enter a product number from the lisp.");
					}
				}
				tdb.delete(tdb.getbyID(index));			
			}
		}
		
		private static void printDaysToNext() {
			//allows user to see the days until next product they have to complete
			List<Product> product = tdb.get(false);
			String s = "";
			if(product.size() == 0) {
				Console.println("Add some products.");
			} else {
				OptionalInt dtnt = product.stream().mapToInt(n -> n.getId()).min();
				if(dtnt.getAsInt() >= 0) {
					s = "\nYou have " + dtnt.getAsInt() + " days until your next product\n";				
				} else {
					s = "\nYou are " + Math.abs(dtnt.getAsInt()) + " days late on completing a product??!?!?!\n";
				}
				Console.println(s);			
			}
		}
		
		private static void printProducts() {
			//allows user to view the pending products in the db
			String row = "";
			List<Product> product = tdb.getAll();
			if(product.size() == 0) {
				Console.println("Add some entries to display them.");
			} else {
				for(Product p : product) {
					row += p.toString();
				}
				displayProducts(row);					
			}
		}

}
