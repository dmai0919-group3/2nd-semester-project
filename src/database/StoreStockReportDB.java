package database;

import model.Product;
import model.Store;
import model.StoreStockReport;
import model.StoreStockReportItem;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StoreStockReportDB implements DBInterface<StoreStockReport> {
    DBConnection db = DBConnection.getInstance();

    /**
     * This method takes an object and converts it to a valid SQL INSERT query, which is the executed
     * Given a valid StockReport object which doesn't exist in the database, it inserts it into the DB
     * @param value it's the given T type object (in this case StockReport)
     * @return the generated key after the insertion to the DB
     * @see DBConnection executeInsertWithID() method
     */
    @Override
    public int create(StoreStockReport value) throws DataAccessException {
        String queryReport = "INSERT INTO 'StoreStockReport' (storeID, date, note) VALUES (?, ?, ?);";
        String queryItem = "INSERT INTO 'StoreStockReportItem' (storeStockReportID, quantity, productID) VALUES (?, ?, ?);";
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
     * Given an ID this method returns a single StoreStockReport which has the given ID
     * @param id is the ID which we want to search for in the database
     * @return the single object with the given ID
     * @see DBConnection executeSelect() method
     */
    @Override
    public StoreStockReport selectByID(int id) throws DataAccessException {
        StoreStockReport report;
        String queryReport = "SELECT TOP 1 * FROM 'StoreStockReport' WHERE id=?";
        String queryItem = "SELECT * FROM 'StoreStockReportItem' WHERE storeStockReportID=?;";
        UserDB userDB = new UserDB();
        ProductDB productDB = new ProductDB();
        List<StoreStockReportItem> items = new LinkedList<>();
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(queryReport);
            s.setInt(1, id);
            ResultSet rsStore = db.executeSelect(s.toString());
            s = db.getDBConn().prepareStatement(queryItem);
            s.setInt(1, id);
            ResultSet rsItem = db.executeSelect(s.toString());
            s.close();
            while (rsItem.next()) {
                items.add(new StoreStockReportItem(
                        productDB.selectByID(rsItem.getInt("productID")),
                        rsItem.getInt("quantity")
                ));
            }
            if (rsStore.next()) {
                report = new StoreStockReport(
                        rsStore.getInt("id"),
                        rsStore.getDate("date"),
                        rsStore.getString("note"),
                        (Store) userDB.selectByID(rsStore.getInt("date")),
                        items
                );
                return report;
            } else throw new DataAccessException("There are no reports with the given ID");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
        }
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
    public ResultSet selectByString(String column, String value) throws DataAccessException {
        StoreStockReport report;
        String queryReport = "SELECT * FROM 'StoreStockReport' WHERE ?=?";
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(queryReport);
            s.setString(1, column);
            s.setString(2, value);
            ResultSet rsStore = db.executeSelect(s.toString());
            return rsStore;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
        }
    }

    /**
     * This method takes an object and converts it to a valid SQL UPDATE query, which is the executed
     *
     * @param value it's the given T type object
     * @return the number of rows affected by the update
     * @see DBConnection executeQuery() method
     */
    @Override
    public int update(StoreStockReport value) throws DataAccessException {
        String queryReport = "UPDATE 'StoreStockReport' SET (date=?, note=?) WHERE id=? AND storeID=?;";
        String queryItem = "UPDATE 'StoreStockReportItem' SET (quantity=?) WHERE stpreStockReportID=?;";
        int rows = -1;
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(queryReport);
            s.setDate(1, value.getDate());
            s.setString(2, value.getNote());
            s.setInt(3, value.getId());
            s.setInt(4, value.getStore().getId());
            rows = db.executeQuery(s.toString());
            for (StoreStockReportItem item : value.getItems()) {
                s = db.getDBConn().prepareStatement(queryItem);
                s.setInt(1, item.getQuantity());
                s.setInt(2, value.getId());
                rows += db.executeQuery(s.toString());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
        }
        return rows;
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
