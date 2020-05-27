package database;

import model.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is used in connection with the DAO pattern
 */
public class WarehouseOrderDB implements DAOInterface<WarehouseOrder> {

    /**
     * This method takes a WarehouseOrder and converts it to a valid SQL INSERT query, which is the executed
     * @param value it's the given WarehouseOrder Model
     * @return the generated key after the insertion to the DB
     * @see DBConnection executeInsertWithID() method
     */
    public int create(WarehouseOrder value) throws DataAccessException {
        DBConnection dbConn = DBConnection.getInstance();
        Connection con = dbConn.getDBConn();

        String pstmtString = "INSERT INTO WarehouseOrder (providerID, warehouseID, date, status) VALUES (?, ?, ?, ?) ;";

        try (PreparedStatement pstmt = con.prepareStatement(pstmtString, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, value.getProvider().getId());
            pstmt.setInt(2, value.getWarehouse().getId());
            pstmt.setDate(2, Date.valueOf(value.getDate()));
            pstmt.setString(3, value.getStatus().name());

            return dbConn.executeQuery(pstmt);
        } catch (Exception e) {
            throw new DataAccessException();
        }
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

        String query = "SELECT * FROM WarehouseOrder WHERE id=?";

        try (PreparedStatement s = con.prepareStatement(query)) {
            s.setInt(1, id);

            ResultSet rs = dbConn.executeSelect(s);

            if (rs.next()) {
                DAOInterface<Warehouse> warehouseDAO = new WarehouseDB();
                DAOInterface<Provider> providerDAO = new ProviderDB();
                Warehouse warehouse = warehouseDAO.selectByID(rs.getInt("warehouseID"));
                Provider provider = providerDAO.selectByID(rs.getInt("providerID"));

                // TODO : Implement Warehouse Order Items
                List<WarehouseOrderItem> warehouseOrderItems = null;

                return new WarehouseOrder(
                        rs.getInt("id"),
                        rs.getDate("date").toLocalDate(),
                        Status.valueOf(rs.getString("status")),
                        warehouse,
                        provider,
                        warehouseOrderItems
                );
            }
        }
        catch (Exception e) {
        	throw new DataAccessException();
        }

        return null;
    }

	@Override
	public List<WarehouseOrder> all() throws DataAccessException {
        // TODO Implement this
        return new LinkedList<>();
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

        String query = "UPDATE WarehouseOrder SET date = ?, status = ? WHERE id=?;";

        // TODO: Also WarehousOrderRevision should be added when update happened
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setDate(1, Date.valueOf(value.getDate()));
            pstmt.setString(2, value.getStatus().name());
            pstmt.setInt(3, value.getId());

            return dbConn.executeQuery(pstmt);
        } catch (Exception e) {
            throw new DataAccessException();
        }
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

        String pstmtString = "DELETE FROM WarehouseOrder WHERE id=?;";

        try (PreparedStatement pstmt = con.prepareStatement(pstmtString)) {
            pstmt.setInt(1, value.getId());

            return dbConn.executeQuery(pstmt);
        } catch (Exception e) {
            throw new DataAccessException();
        }
    }

}
