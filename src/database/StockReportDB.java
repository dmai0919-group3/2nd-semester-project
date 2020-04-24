package database;

import model.StoreStockReport;
import model.StoreStockReportItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockReportDB implements DBInterface<StoreStockReport> {
    DBConnection db = DBConnection.getInstance();

    /**
     * This method takes an object and converts it to a valid SQL INSERT query, which is the executed
     * Given a valid StockReport object which doesn't exist in the database, it inserts it into the DB
     * @param value it's the given T type object (in this case StockRepoort)
     * @return the generated key after the insertion to the DB
     * @see DBConnection executeInsertWithID() method
     */
    @Override
    public int create(StoreStockReport value) throws DataAccessException {
        String queryReport = "INSERT INTO 'StoreStockReport' (storeID, date, note) VALUES (?, ?, ?);";
        String queryItem = "INSERT INTO 'StoreStockReportItem' (stockReportID, quantity, productID) VALUES (?, ?, ?);";
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(queryReport);
            s.setInt(1, value.getStore().getId());
            s.setDate(2, value.getDate());
            s.setString(3, value.getNote());
            int id = db.executeInsertWithID(s.toString());
            for (StoreStockReportItem item : value.getItems()) {
                s = db.getDBConn().prepareStatement(queryItem);
                s.setInt(1, id);
                s.setInt(2, item.getQuantity());
                s.setInt(3, item.getProduct().getId());
                db.executeInsertWithID(s.toString());
            }
            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
        }
    }

    /**
     * This method takes an ID and converts it to a valid SQL SELECT query, which is the executed
     *
     * @param id is the ID which we want to search for in the database
     * @return the single object with the given ID
     * @see DBConnection executeSelect() method
     */
    @Override
    public StoreStockReport selectByID(int id) {
        return null;
    }

    /**
     * This method takes a column name and a search value, converts it to a valid SQL SELECT query, which is the executed
     *
     * @param column the columns name we want to search in
     * @param value  the value we want to search for
     * @return the ResultSet containing all the results of the query
     * @see DBConnection executeSelect() method
     */
    @Override
    public ResultSet selectByString(String column, String value) {
        return null;
    }

    /**
     * This method takes an object and converts it to a valid SQL UPDATE query, which is the executed
     *
     * @param value it's the given T type object
     * @return the number of rows affected by the update
     * @see DBConnection executeQuery() method
     */
    @Override
    public int update(StoreStockReport value) {
        return 0;
    }

    /**
     * This method takes an object and converts it to a valid SQL DELETE query, which is the executed
     *
     * @param value it's the given T type object
     * @return the number of rows deleted from the table
     * @see DBConnection executeQuery()
     */
    @Override
    public int delete(StoreStockReport value) {
        return 0;
    }
}
