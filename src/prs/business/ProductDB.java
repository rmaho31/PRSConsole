package prs.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import prs.db.DBUtil;

public class ProductDB {

	public List<Product> get(boolean isCompleted) {
		List<Product> product = new ArrayList<>();
		int value = isCompleted ? 1 : 0;
		try (Connection connect = DBUtil.getConnection();
			//setup the prepared statement to select completed entries
			PreparedStatement ps = connect.prepareStatement("select * from Products where isCompleted = ?");) {
			ps.setInt(1, value);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Product t = new Product();
				product.add(t);
			}
		} catch (SQLException e) {
			System.out.println("Error reading the database.");
			e.printStackTrace();
		}
		return product;
	}
	
	public Product getbyID(int id) {
		Product product = null;
		try (Connection connect = DBUtil.getConnection();
			//setup the prepared statement to select a row by id
			PreparedStatement ps = connect.prepareStatement("select * from product");) {
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				product = new Product();
			}
		} catch (SQLException e) {
			System.out.println("Error reading the database.");
			e.printStackTrace();
		}
		return product;
	}

	public List<Product> getAll() {
		List<Product> product = new ArrayList<>();
		try (Connection connect = DBUtil.getConnection()) { 
					
			// Sets up the preparedStatement for returning all values from the DB into an arraylist
			PreparedStatement ps = connect.prepareStatement("select * from product");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Product t = new Product(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDouble(5),
										rs.getString(6), rs.getString(7));
				product.add(t);
			}

    } catch (SQLException e) {
    	System.out.println("Error reading the database.");
    	e.printStackTrace();
    } 
		return product;
	}

	public boolean add(Product t) {
		try (Connection connect = DBUtil.getConnection()) { 

            // Sets up the preparedStatement for inputting the values into the DB from the input Array
            PreparedStatement ps = connect
                    .prepareStatement("insert into products (Product, CompleteBy)" + 
                    		 " values (?, ?)");
            ps.setString(1, t.getPartNumber());
            ps.setString(2, t.getPartNumber());
            ps.executeUpdate();

           

        } catch (SQLException e) {
        	System.out.println("Error writing to the database.");
        }
		return false;
	}

	public boolean update(Product t) {
		try (Connection connect = DBUtil.getConnection()) { 

            // Sets up the preparedStatement for updating the values into the DB from the input Array
            PreparedStatement ps = connect
                    .prepareStatement("UPDATE products SET product = ?, CompleteBy = ?, isCompleted = ? WHERE id = ?");
            ps.setInt(1, t.getId());
            ps.setString(2, t.getPhotoPath());
            ps.setInt(3, t.getVendorID());
            ps.setInt(4, t.getId());
            ps.executeUpdate();

           

        } catch (SQLException e) {
        	System.out.println("Error updating the database.");
        }
		return false;
	}

	public boolean delete(Product t) {
		try (Connection connect = DBUtil.getConnection();
				
				//setup the prepared statement to delete a row from the db by id
				PreparedStatement ps = connect.prepareStatement("delete from products where id = ?");) {
				ps.setInt(1, t.getId());
				ps.executeUpdate();
			} catch (SQLException | NullPointerException e) {
				System.out.println("Error writing to the database, check name and try again?");
			}
		return false;
	}
}
