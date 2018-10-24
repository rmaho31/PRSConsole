package prs.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import prs.db.DBUtil;

public class UserDB {
	
	public User getbyID(int id) {
		User user = null;
		try (Connection connect = DBUtil.getConnection();
			//setup the prepared statement to select a row by id
			PreparedStatement ps = connect.prepareStatement("select * from user where id = ?");) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				user = getUser(rs);
			}
		} catch (SQLException e) {
			System.out.println("Error reading the database.");
			e.printStackTrace();
		}
		return user;
	}

	public List<Object> getAll() {
		List<Object> user = new ArrayList<>();
		try (Connection connect = DBUtil.getConnection()) { 
					
			// Sets up the preparedStatement for returning all values from the DB into an arraylist
			PreparedStatement ps = connect.prepareStatement("select * from user");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				User u = getUser(rs);
				user.add(u);
			}

    } catch (SQLException e) {
    	System.out.println("Error reading the database.");
    	e.printStackTrace();
    } 
		return user;
	}

	public boolean add(User u) {
		try (Connection connect = DBUtil.getConnection()) { 

            // Sets up the preparedStatement for inputting the values into the DB from the input Array
            PreparedStatement ps = connect
                    .prepareStatement("insert into user (username, password, FirstName, LastName, "
                    		+ "PhoneNumber, Email, IsReviewer, IsAdmin)" + 
                    		 " values (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, u.getUserName());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFirstName());
            ps.setString(4, u.getLastName());
            ps.setString(5, u.getPhoneNumber());
            ps.setString(6, u.getEmail()); 
            ps.setBoolean(7, u.isReviewer());
            ps.setBoolean(8, u.isAdmin());
            ps.executeUpdate();           

        } catch (SQLException e) {
        	System.out.println("Error writing to the database.");
        	e.printStackTrace();
        	return false;
        }
		return true;
	}

	public boolean update(User u) {
		try (Connection connect = DBUtil.getConnection()) { 

            // Sets up the preparedStatement for updating the values into the DB from the input Array
            PreparedStatement ps = connect
                    .prepareStatement("UPDATE user SET username = ?, password = ?, FirstName = ?, LastName = ?, " + 
                    				  "PhoneNumber = ?, Email = ?, IsReviewer = ?, IsAdmin = ? WHERE id = ?");
            ps.setString(1, u.getUserName());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFirstName());
            ps.setString(4, u.getLastName());
            ps.setString(5, u.getPhoneNumber());
            ps.setString(6, u.getEmail()); 
            ps.setBoolean(7, u.isReviewer());
            ps.setBoolean(8, u.isAdmin());
            ps.setInt(9, u.getId());
            ps.executeUpdate();           

        } catch (SQLException e) {
        	System.out.println("Error updating the database.");
        	e.printStackTrace();
        	return false;
        }
		return true;
	}

	public boolean delete(User u) {
		try (Connection connect = DBUtil.getConnection();
				
				//setup the prepared statement to delete a row from the db by id
				PreparedStatement ps = connect.prepareStatement("delete from user where id = ?");) {
				ps.setInt(1, u.getId());
				ps.executeUpdate();
			} catch (SQLException | NullPointerException e) {
				System.out.println("Error writing to the database");
				e.printStackTrace();
				return false;
			}
		return true;
	}
	private User getUser(ResultSet rs) throws SQLException {
		User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							    rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getBoolean(9));
		return u;
	}
}
