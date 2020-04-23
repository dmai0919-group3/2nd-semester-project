package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Order;

public class OrderDB implements DBInterface<Order> {
	
	DBConnection db = DBConnection.getInstance();

	@Override
	public int create(Order value) {
		String query = "INSERT INTO 'Order' ('storeID', 'warehouseID', 'price', 'date') VALUES (?,?,?,?);";
		
		int orderID = -1;
		try {
			PreparedStatement s = db.getDBConn().prepareStatement(query);
			
			// TODO
			/*
			 * set values to each parameter
			 */
			/*s.setInt(1, 0);
			s.setInt(2, 0);
			s.setDouble(3, 0);
			s.setDate(4, null);*/
			
			orderID = db.executeInsertWithID(query);
			
			// TODO
			/*
			 * Create status for PENDING after creating the order
			 */
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return orderID;
	}

	@Override
	public Order selectByID(int id) {
		String query = "SELECT * FROM 'Order' WHERE id=?;";
		
		try {
			PreparedStatement s = db.getDBConn().prepareStatement(query);
			s.setInt(1, id);
			
			ResultSet rs = db.executeSelect(s.toString());
			if (rs.next()) {
				// TODO
				/*
				 * Update order model to match database
				 */
				/*return new Order(
						rs.getInt("id"),
						rs.getInt("storeID"),
						rs.getInt("warehouseID"),
						rs.getDouble("price"),
						rs.getDate("date"));*/
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public ResultSet selectByString(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Order value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Order value) {
		String query = "DELETE FROM 'Order' WHERE id=?";
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(query);
            s.setInt(1, value.getId());
            return db.executeQuery(s.toString());
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        }
        return -1;
	}

}
