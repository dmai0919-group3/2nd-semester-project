package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DBInterface;
import model.WarehouseOrder;
import model.Warehouse;

/**
 * This class is used in connection with the DAO pattern
 */
public class WarehouseOrderDB implements DBInterface<WarehouseOrder> {
    DBConnection db = DBConnection.getInstance();

    private static String tableName = "WarehouseOrder";


    /**
     * This method takes a WarehouseOrder and converts it to a valid SQL INSERT query, which is the executed
     * @param value it's the given WarehouseOrder Model
     * @return the generated key after the insertion to the DB
     * @see DBConnection executeInsertWithID() method
     */
    public int create(WarehouseOrder value) throws DataAccessException {
        String query = "INSERT INTO ? (date, status) VALUES ? , ? ;";

        int resultID = -1;
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(query);

            s.setString(1, WarehouseOrderDB.tableName);
            s.setDate(2, Date.valueOf(value.getDate()));
            s.setString(3, value.getStatus());

            resultID = db.executeInsertWithID(s.toString());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
        }
        return resultID;
     }

    /**
     * This method takes an ID and converts it to a valid SQL SELECT query, which is the executed
     * @param id is the ID which we want to search for in the database
     * @return the single WarehouseOrder with the given ID
     * @see DBConnection executeSelect() method
     */
    public WarehouseOrder selectByID(int id) throws DataAccessException {
        String query = "SELECT TOP 1 * FROM '?' WHERE id=?;";

        Warehouse warehouse = null; // TODO see how to retrieve warehouse
        try {
	        PreparedStatement s = db.getDBConn().prepareStatement(query);
	        s.setString(1, WarehouseOrderDB.tableName);
	        s.setInt(2, id);

	        ResultSet rs = db.executeSelect(s.toString());
	
            if (rs.first()) {
                return new WarehouseOrder (
                        rs.getInt("id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("status"),
                        warehouse);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
        }
        return null;
    }

	/**
     * This method takes a column name and a search value, converts it to a valid SQL SELECT query, which is the executed
     * @param column the columns name we want to search in
     * @param value the value we want to search for
     * @return the ResultSet containing all the results of the query
     * @see DBConnection executeSelect() method
     */
    public ResultSet selectByString(String column, String value) throws DataAccessException {
        String query = "SELECT * FROM '?' WHERE ?=?;";
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(query);

            s.setString(1, WarehouseOrderDB.tableName);
            s.setString(2, column);
            s.setString(3, value);

            return db.executeSelect(s.toString());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
        }
    }

    /**
     * This method takes an object and converts it to a valid SQL UPDATE query, which is the executed
     * @param value it's the given T type object
     * @return the number of rows affected by the update
     * @see DBConnection executeQuery() method
     */
    public int update(WarehouseOrder value) throws DataAccessException {
        String query= "UPDATE '?' SET date = ?, status = ? WHERE id=?;";
        // TODO : Add the Warehouse to update
        int rows = -1;
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(query);

	        s.setString(1, WarehouseOrderDB.tableName);
	        s.setDate(2, Date.valueOf(value.getDate()));
	        s.setString(3, value.getStatus());
	        s.setInt(4, value.getId());
            
            rows = db.executeQuery(s.toString());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
        }
        return rows;
    }

    /**
     * This method takes an object and converts it to a valid SQL DELETE query, which is the executed
     * @param value it's the given WarehouseOrder
     * @return the number of rows deleted from the table
     * @see DBConnection executeQuery()
     */
    public int delete(WarehouseOrder value) throws DataAccessException {
        String query = "DELETE FROM '?' WHERE id=?";
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(query);

            s.setString(1, WarehouseOrderDB.tableName);
            s.setInt(2, value.getId());

            return db.executeQuery(s.toString());
        } catch (SQLException e) {
             System.err.println(e.getMessage());
             throw new DataAccessException();
        }
    }
}
