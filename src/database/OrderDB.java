package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class OrderDB implements DBInterface<Order> {
	
	DBConnection db = DBConnection.getInstance();

	@Override
	public int create(Order value) {
		String query = "INSERT INTO 'Order' ('storeID', 'warehouseID', 'price', 'date') VALUES (?,?,?,?);";
		
		int orderID = -1;
		try {
			PreparedStatement s = db.getDBConn().prepareStatement(query);

			s.setInt(1, value.getStore().getId());
			s.setInt(2, value.getWarehouse().getId());
			s.setDouble(3, value.getPrice());
			s.setDate(4, Date.valueOf(value.getDate()));
			
			orderID = db.executeInsertWithID(query);
			s.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return orderID;
	}

	@Override
	public Order selectByID(int id) throws DataAccessException {
		// TODO
		/*
		 * Join warehouse, store, OrderItem and orderStatus tables
		 */
		String query = "SELECT Order.*, Warehouse.* FROM 'Order' JOIN 'Warehouse' ON Order.warehouseID = Warehouse.id WHERE order.id=?;";
		
		try {
			PreparedStatement s = db.getDBConn().prepareStatement(query);
			
			s.setInt(1, id);
			
			ResultSet rs = db.executeSelect(s.toString());
			if (rs.next()) {
				// TODO Implement query's for OrderRevisions and OrderItems
				/*
				 * Create the object basec on the JOIN QUERY
				 */
				UserDB userDB = new UserDB();
				return new Order(
						rs.getInt("id"),
						rs.getDate("date").toLocalDate(),
						rs.getDouble("price"),
						(Warehouse) userDB.selectByID(rs.getInt("warehouseID")),
						(Store) userDB.selectByID(rs.getInt("storeID")),
						new ArrayList<>(),
						new ArrayList<>());
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public ResultSet selectByString(String column, String value) {
		String query = "SELECT * FROM 'Order' WHERE ?=?;";
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(query);
            
            s.setString(1, column);
            s.setString(2, value);
            
            return db.executeSelect(s.toString());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
	}

	@Override
	public int update(Order value) {
		String query = "UPDATE 'Order' SET (storeID=?, warehouse=?, price=?, date=?) WHERE id=" + value.getId() + ";";
		
		int rows = -1;
		try {
			PreparedStatement s = db.getDBConn().prepareStatement(query);
			
			// TODO
			/*
			 * Match query with order model
			 */
			s.setInt(1, value.getStore().getId());
			s.setInt(2, value.getWarehouse().getId());
			s.setDouble(3, value.getPrice());
			//s.setDate(4, value.getDate());
			
			rows = db.executeQuery(s.toString());
		} catch (SQLException e) {
            System.err.println(e.getMessage());
        }
		return rows;
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
