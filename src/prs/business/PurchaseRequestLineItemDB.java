package prs.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import prs.db.DBUtil;

public class PurchaseRequestLineItemDB {
	
	public PurchaseRequestLineItem getbyID(int id) {
		PurchaseRequestLineItem purchaseRequestLineItem = null;
		try (Connection connect = DBUtil.getConnection();
			//setup the prepared statement to select a row by id
			PreparedStatement ps = connect.prepareStatement("select * from purchaseRequestLineItem where id = ?");) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				purchaseRequestLineItem = getPurchaseRequestLineItem(rs);
			}
		} catch (SQLException e) {
			System.out.println("Error reading the database.");
			e.printStackTrace();
		}
		return purchaseRequestLineItem;
	}

	public List<Object> getAll() {
		List<Object> purchaseRequestLineItem = new ArrayList<>();
		try (Connection connect = DBUtil.getConnection()) { 
					
			// Sets up the preparedStatement for returning all values from the DB into an arraylist
			PreparedStatement ps = connect.prepareStatement("select * from purchaseRequestLineItem");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				PurchaseRequestLineItem p = getPurchaseRequestLineItem(rs);
				purchaseRequestLineItem.add(p);
			}

    } catch (SQLException e) {
    	System.out.println("Error reading the database.");
    	e.printStackTrace();
    } 
		return purchaseRequestLineItem;
	}

	public boolean add(PurchaseRequestLineItem prli) {
		try (Connection connect = DBUtil.getConnection()) { 

            // Sets up the preparedStatement for inputting the values into the DB from the input Array
            PreparedStatement ps = connect
                    .prepareStatement("insert into purchaseRequestLineItem (PurchaseRequestID, ProductID,"
                    		+ " Quantity) values (?, ?, ?)");
            ps.setInt(1, prli.getPurchaseRequestID());
            ps.setInt(2, prli.getProductID());
            ps.setInt(3, prli.getQuantity());
            ps.executeUpdate();           

        } catch (SQLException e) {
        	System.out.println("Error writing to the database.");
        	e.printStackTrace();
        	return false;
        }
		return true;
	}

	public boolean update(PurchaseRequestLineItem prli) {
		try (Connection connect = DBUtil.getConnection()) { 

            // Sets up the preparedStatement for updating the values into the DB from the input Array
            PreparedStatement ps = connect
                    .prepareStatement("UPDATE purchaseRequestLineItem SET PurchaseRequestID = ?, ProductID = ?, Quantity = ? WHERE id = ?");
            ps.setInt(1, prli.getPurchaseRequestID());
            ps.setInt(2, prli.getProductID());
            ps.setInt(3, prli.getQuantity());
            ps.executeUpdate();              

        } catch (SQLException e) {
        	System.out.println("Error updating the database.");
        	e.printStackTrace();
        	return false;
        }
		return true;
	}

	public boolean delete(PurchaseRequestLineItem p) {
		try (Connection connect = DBUtil.getConnection();
				
				//setup the prepared statement to delete a row from the db by id
				PreparedStatement ps = connect.prepareStatement("delete from purchaseRequestLineItem where id = ?");) {
				ps.setInt(1, p.getId());
				ps.executeUpdate();
			} catch (SQLException | NullPointerException e) {
				System.out.println("Error writing to the database");
				e.printStackTrace();
				return false;
			}
		return true;
	}
	private PurchaseRequestLineItem getPurchaseRequestLineItem(ResultSet rs) throws SQLException {
		PurchaseRequestLineItem p = new PurchaseRequestLineItem(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
		return p;
	}
}
