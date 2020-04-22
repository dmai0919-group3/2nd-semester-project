package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Order;

public class OrderDB implements DBInterface<Order> {
	
	DBConnection db = DBConnection.getInstance();

	@Override
	public int create(Order value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Order selectByID(int id) {
		// TODO Auto-generated method stub
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
