package prs.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import prs.business.Product;
import prs.db.DBUtil;

public class DBjoins {

	public class ProductDB {
		
		public Product getbyID(int id) {
			Product product = null;
			try (Connection connect = DBUtil.getConnection();
				//setup the prepared statement to select a row by id
				PreparedStatement ps = connect.prepareStatement("select * from product where id = ?");) {
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					
					product = getProduct(rs);
				}
			} catch (SQLException e) {
				System.out.println("Error reading the database.");
				e.printStackTrace();
			}
			return product;
		}

		public List<Object> getAll() {
			List<Object> product = new ArrayList<>();
			try (Connection connect = DBUtil.getConnection()) { 
						
				// Sets up the preparedStatement for returning all values from the DB into an arraylist
				PreparedStatement ps = connect.prepareStatement("select * from product");
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					
					Product t = getProduct(rs);
					product.add(t);
				}

	    } catch (SQLException e) {
	    	System.out.println("Error reading the database.");
	    	e.printStackTrace();
	    } 
			return product;
		}

		public boolean add(Product p) {
			try (Connection connect = DBUtil.getConnection()) { 

	            // Sets up the preparedStatement for inputting the values into the DB from the input Array
	            PreparedStatement ps = connect
	                    .prepareStatement("insert into product (VendorID, partNumber, name, price)" + 
	                    		 " values (?, ?, ?, ?)");
	            ps.setInt(1, p.getVendorID());
	            ps.setString(2, p.getPartNumber());
	            ps.setString(3, p.getName());
	            ps.setDouble(4, p.getPrice());
	            ps.executeUpdate();

	           

	        } catch (SQLException e) {
	        	System.out.println("Error writing to the database.");
	        	e.printStackTrace();
	        }
			return false;
		}

		public boolean update(Product p) {
			try (Connection connect = DBUtil.getConnection()) { 

	            // Sets up the preparedStatement for updating the values into the DB from the input Array
	            PreparedStatement ps = connect
	                    .prepareStatement("UPDATE product SET name = ? WHERE id = ?");
	            ps.setString(1, p.getName());
	            ps.setDouble(2, p.getId());
	            ps.executeUpdate();

	           

	        } catch (SQLException e) {
	        	System.out.println("Error updating the database.");
	        	e.printStackTrace();
	        }
			return false;
		}

		public boolean delete(Product p) {
			try (Connection connect = DBUtil.getConnection();
					
					//setup the prepared statement to delete a row from the db by id
					PreparedStatement ps = connect.prepareStatement("delete from product where id = ?");) {
					ps.setInt(1, p.getId());
					ps.executeUpdate();
				} catch (SQLException | NullPointerException e) {
					System.out.println("Error writing to the database, check name and try again?");
					e.printStackTrace();
				}
			return false;
		}
		private Product getProduct(ResultSet rs) throws SQLException {
			Product p = new Product(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDouble(5),
								    rs.getString(6), rs.getString(7));
			return p;
		}
	}
}
