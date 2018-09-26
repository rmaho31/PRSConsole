package prs.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import prs.db.DBUtil;

public class VendorDB {
	
	public Vendor getbyID(int id) {
		Vendor vendor = null;
		try (Connection connect = DBUtil.getConnection();
			//setup the prepared statement to select a row by id
			PreparedStatement ps = connect.prepareStatement("select * from vendor where id = ?");) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				vendor = getVendor(rs);
			}
		} catch (SQLException e) {
			System.out.println("Error reading the database.");
			e.printStackTrace();
		}
		return vendor;
	}

	public List<Vendor> getAll() {
		List<Vendor> vendor = new ArrayList<>();
		try (Connection connect = DBUtil.getConnection()) { 
					
			// Sets up the preparedStatement for returning all values from the DB into an arraylist
			PreparedStatement ps = connect.prepareStatement("select * from vendor");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Vendor v = getVendor(rs);
				vendor.add(v);
			}

    } catch (SQLException e) {
    	System.out.println("Error reading the database.");
    	e.printStackTrace();
    } 
		return vendor;
	}

	public boolean add(Vendor v) {
		try (Connection connect = DBUtil.getConnection()) { 

            // Sets up the preparedStatement for inputting the values into the DB from the input Array
            PreparedStatement ps = connect
                    .prepareStatement("insert into vendor (Code, Name, Address, City, State, Zip, PhoneNumber, Email, IsPreApproved)" + 
                    		 " values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(2, v.getCode());
            ps.setString(3, v.getName());
            ps.setString(4, v.getAddress());
            ps.setString(2, v.getCity());
            ps.setString(3, v.getState());
            ps.setString(4, v.getZip());
            ps.setString(2, v.getPhoneNumber());
            ps.setString(3, v.getEmail());
            ps.setBoolean(4, v.isPreApproved());            
            ps.executeUpdate();           

        } catch (SQLException e) {
        	System.out.println("Error writing to the database.");
        	e.printStackTrace();
        	return false;
        }
		return true;
	}

	public boolean update(Vendor v) {
		try (Connection connect = DBUtil.getConnection()) { 

            // Sets up the preparedStatement for updating the values into the DB from the input Array
            PreparedStatement ps = connect
                    .prepareStatement("UPDATE vendor SET Code = ?, Name = ?, Address = ?, City = ?, State = ?, Zip = ?,"
                    		+ " PhoneNumber = ?, Email = ?, IsPreApproved = ? WHERE id = ?");
            ps.setString(1, v.getCode());
            ps.setString(2, v.getName());
            ps.setString(3, v.getAddress());
            ps.setString(4, v.getCity());
            ps.setString(5, v.getState());
            ps.setString(6, v.getZip());
            ps.setString(7, v.getPhoneNumber());
            ps.setString(8, v.getEmail());
            ps.setBoolean(9, v.isPreApproved());
            ps.setInt(10, v.getId());
            ps.executeUpdate();           

        } catch (SQLException e) {
        	System.out.println("Error updating the database.");
        	e.printStackTrace();
        	return false;
        }
		return true;
	}

	public boolean delete(Vendor v) {
		try (Connection connect = DBUtil.getConnection();
				
				//setup the prepared statement to delete a row from the db by id
				PreparedStatement ps = connect.prepareStatement("delete from vendor where id = ?");) {
				ps.setInt(1, v.getId());
				ps.executeUpdate();
			} catch (SQLException | NullPointerException e) {
				System.out.println("Error writing to the database, check name and try again?");
				e.printStackTrace();
			}
		return false;
	}
	
	private Vendor getVendor(ResultSet rs) throws SQLException {
		Vendor v = new Vendor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							    rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getBoolean(10));
		return v;
	}	
}
