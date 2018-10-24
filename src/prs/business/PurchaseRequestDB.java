package prs.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import prs.db.DBUtil;

public class PurchaseRequestDB {
	
	public PurchaseRequest getbyID(int id) {
		PurchaseRequest purchaseRequest = null;
		try (Connection connect = DBUtil.getConnection();
			//setup the prepared statement to select a row by id
			PreparedStatement ps = connect.prepareStatement("select * from purchaseRequest where id = ?");) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				purchaseRequest = getPurchaseRequest(rs);
			}
		} catch (SQLException e) {
			System.out.println("Error reading the database.");
			e.printStackTrace();
		}
		return purchaseRequest;
	}

	public List<Object> getAll() {
		List<Object> purchaseRequest = new ArrayList<>();
		try (Connection connect = DBUtil.getConnection()) { 
					
			// Sets up the preparedStatement for returning all values from the DB into an arraylist
			PreparedStatement ps = connect.prepareStatement("select * from purchaseRequest");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				PurchaseRequest p = getPurchaseRequest(rs);
				purchaseRequest.add(p);
			}

    } catch (SQLException e) {
    	System.out.println("Error reading the database.");
    	e.printStackTrace();
    } 
		return purchaseRequest;
	}

	public boolean add(PurchaseRequest p, User u) {
		try (Connection connect = DBUtil.getConnection()) { 

            // Sets up the preparedStatement for inputting the values into the DB from the input Array
            PreparedStatement ps = connect
                    .prepareStatement("insert into purchaseRequest (UserID, Description, Justification,"
                    		+ " DateNeeded, DeliveryMode, Status, Total, SubmittedDate, ReasonForRejection)" + 
                    		 " values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, u.getId());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getJustification());
            ps.setString(4, p.getDateNeeded().toString());
            ps.setString(5, p.getDeliveryMode());
            ps.setString(6, p.getStatus());
            ps.setDouble(7, p.getTotal());
            ps.setString(8, p.getSubmittedDate().toString().replace("T", " "));
            ps.setString(9, p.getReasonForRejection());
            ps.executeUpdate();           

        } catch (SQLException e) {
        	System.out.println("Error writing to the database.");
        	e.printStackTrace();
        	return false;
        }
		return true;
	}

	public boolean update(PurchaseRequest p, User u) {
		try (Connection connect = DBUtil.getConnection()) { 

            // Sets up the preparedStatement for updating the values into the DB from the input Array
            PreparedStatement ps = connect
                    .prepareStatement("UPDATE purchaseRequest SET Description = ?, Justification = ?," + 
                    				  " DateNeeded = ?, DeliveryMode = ?, Status = ?, Total = ?, SubmittedDate = ?, ReasaonForRejection = ? WHERE id = ?");
            ps.setString(1, p.getDescription());
            ps.setString(2, p.getJustification());
            ps.setString(3, p.getDateNeeded().toString());
            ps.setString(4, p.getDeliveryMode());
            ps.setString(5, p.getStatus());
            ps.setDouble(6, p.getTotal());
            ps.setString(7, p.getSubmittedDate().toString().replace("T", " "));
            ps.setString(8, p.getReasonForRejection());
            ps.setInt(9, p.getId());            
            ps.executeUpdate();                      

        } catch (SQLException e) {
        	System.out.println("Error updating the database.");
        	e.printStackTrace();
        	return false;
        }
		return true;
	}

	public boolean delete(PurchaseRequest p) {
		try (Connection connect = DBUtil.getConnection();
				
				//setup the prepared statement to delete a row from the db by id
				PreparedStatement ps = connect.prepareStatement("delete from purchaseRequest where id = ?");) {
				ps.setInt(1, p.getId());
				ps.executeUpdate();
			} catch (SQLException | NullPointerException e) {
				System.out.println("Error writing to the database, check name and try again?");
				e.printStackTrace();
				return false;
			}
		return true;
	}
	private PurchaseRequest getPurchaseRequest(ResultSet rs) throws SQLException {
		PurchaseRequest p = new PurchaseRequest(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), LocalDate.parse(rs.getString(5)),
							    rs.getString(6), rs.getString(7), rs.getDouble(8), 
							    LocalDateTime.parse(rs.getString(9).replace(" ", "T")), rs.getString(10));
		return p;
	}
}
