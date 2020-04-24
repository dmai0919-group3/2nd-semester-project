package database;

import java.sql.*;
import java.time.LocalDate;

import database.DBConnection;
import database.DBInterface;
import model.WarehouseOrder;
import model.Warehouse;

/**
 * This class is used in connection with the DAO pattern
 */
public class WarehouseOrderDB implements DBInterface<WarehouseOrder> {

    private static String tableName = "warehouse";


    /**
     * This method takes a WarehouseOrder and converts it to a valid SQL INSERT query, which is the executed
     * @param value it's the given WarehouseOrder Model
     * @return the generated key after the insertion to the DB
     * @see DBConnection executeInsertWithID() method
     */
    public int create(WarehouseOrder value) throws DataAccessException {
        DBConnection dbConn = DBConnection.getInstance();
        Connection con = dbConn.getDBConn();

        String pstmtString = "INSERT INTO ? (date, status)";
        pstmtString += "VALUES ? , ? ;";

        int resultId = 0;
        try {
        	PreparedStatement pstmt = con.prepareStatement(pstmtString);
	        pstmt.setString(1, WarehouseOrderDB.tableName);
	        pstmt.setDate(2, Date.valueOf(value.getDate()));
	        pstmt.setString(3, value.getStatus());
	        
	        String query = "";
	        resultId = dbConn.executeQuery(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
        }


        return resultId;
    }

    /**
     * This method takes an ID and converts it to a valid SQL SELECT query, which is the executed
     * @param id is the ID which we want to search for in the database
     * @return the single WarehouseOrder with the given ID
     * @see DBConnection executeSelect() method
     */
    public WarehouseOrder selectByID(int id) throws DataAccessException {
        DBConnection dbConn = DBConnection.getInstance();
        Connection con = dbConn.getDBConn();

        String pstmtString = "SELECT id, date, status, warehouseOrder";
        pstmtString += "FROM ?";
        pstmtString += "WHERE id=?";

        WarehouseOrder result = null;
        try {
	        PreparedStatement pstmt = con.prepareStatement(pstmtString);
	        pstmt.setString(1, WarehouseOrderDB.tableName);
	        pstmt.setInt(2, id);
	
	        String query = "";
	        ResultSet rs = dbConn.executeSelect(query);
	
	        if (!rs.first()) {
	            return null;
	        }
	        
	        Warehouse warehouse = null;
	        result = new WarehouseOrder(rs.getInt("id"), rs.getDate("date").toLocalDate(), rs.getString("status"), warehouse);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
        }

        return result;
    }

	/**
     * This method takes a column name and a search value, converts it to a valid SQL SELECT query, which is the executed
     * @param column the columns name we want to search in
     * @param value the value we want to search for
     * @return the ResultSet containing all the results of the query
     * @see DBConnection executeSelect() method
     */
    public ResultSet selectByString(String column, String value) throws DataAccessException {
        DBConnection dbConn = DBConnection.getInstance();
        Connection con = dbConn.getDBConn();

        String pstmtString = "SELECT id, date, status, warehouseOrder";
        pstmtString += "FROM ?";
        pstmtString += "WHERE ? = ?";

        ResultSet rs = null;
        try {
	        PreparedStatement pstmt = con.prepareStatement(pstmtString);
	        pstmt.setString(1, WarehouseOrderDB.tableName);
	        pstmt.setString(2, column);
	        pstmt.setString(3, value);
	
	        String query = "";
	        rs = dbConn.executeSelect(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
        }
        
        return rs;
    }

    /**
     * This method takes an object and converts it to a valid SQL UPDATE query, which is the executed
     * @param value it's the given T type object
     * @return the number of rows affected by the update
     * @see DBConnection executeQuery() method
     */
    public int update(WarehouseOrder value) throws DataAccessException {
        DBConnection dbConn = DBConnection.getInstance();
        Connection con = dbConn.getDBConn();

        String pstmtString = "UPDATE ?";
        pstmtString += "SET date = ?, status = ?";
        pstmtString += "WHERE id=?;";

        int resultId = 0;
        try {
	        PreparedStatement pstmt = con.prepareStatement(pstmtString);
	        pstmt.setString(1, WarehouseOrderDB.tableName);
	        pstmt.setDate(2, Date.valueOf(value.getDate()));
	        pstmt.setString(3, value.getStatus());
	        pstmt.setInt(4, value.getId());
	
	        String query = "";
	        resultId = dbConn.executeQuery(query);
	    } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
        }
	    
        return resultId;
    }

    /**
     * This method takes an object and converts it to a valid SQL DELETE query, which is the executed
     * @param value it's the given WarehouseOrder
     * @return the number of rows deleted from the table
     * @see DBConnection executeQuery()
     */
    public int delete(WarehouseOrder value) throws DataAccessException {
        DBConnection dbConn = DBConnection.getInstance();
        Connection con = dbConn.getDBConn();

        String pstmtString = "DELETE FROM ?";
        pstmtString += "WHERE id=?;";
        
        int result = 0;
        
        try {
	        PreparedStatement pstmt = con.prepareStatement(pstmtString);
	        pstmt.setString(1, WarehouseOrderDB.tableName);
	        pstmt.setInt(2, value.getId());
	
	        String query = "";
	        result = dbConn.executeQuery(query);
	    } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
        }
		
        return result;
    }
}
