package database;

import model.Product;
import model.Stock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDB implements DBInterface<Product> {
    DBConnection db = DBConnection.getInstance();

    /**
     * This method takes an object and converts it to a valid SQL INSERT query, which is the executed
     * Given a valid product which doesn't exist in the database, it inserts it to the DB
     * @param value it's the given T type object (in this case Product)
     * @return the generated key after the insertion to the DB
     * @see DBConnection executeInsertWithID() method
     */
    @Override
    public int create(Product value) {
        String queryProd = "INSERT INTO 'Product' ('name', 'weight', 'price', 'minQuantity') VALUES (?, ? ,? , ?);";
        String queryStock = "INSERT INTO 'Stock' ('product_id', 'warehouse_id', 'quantity') VALUES (?, ? ?);";
        List<Stock> stockList = value.getStock();
        int productID = -1;
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(queryProd);
            s.setString(1, value.getName()); // name
            s.setInt(2, value.getWeight()); // weight
            s.setDouble(3, value.getPrice()); // price
            s.setInt(4, value.getMinQuantity()); // minQuantity
            productID = db.executeInsertWithID(s.toString());
            for (Stock stock : stockList) {
                s = db.getDBConn().prepareStatement(queryStock);
                s.setInt(1, productID);
                s.setInt(2, stock.getWarehouse().getId());
                s.setInt(3, stock.getQuantity());
                db.executeInsertWithID(s.toString());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return productID;
    }

    /**
     * This method takes an ID and converts it to a valid SQL SELECT query, which is the executed
     * Given an ID this method returns a single Product which has the given ID
     * @param id is the ID which we want to search for in the database
     * @return the single object with the given ID
     * @see DBConnection executeSelect() method
     */
    @Override
    public Product selectByID(int id) {
        return null;
    }

    /**
     * This method takes a column name and a search value, converts it to a valid SQL SELECT query, which is the executed
     * Given a column (product name) and a value it finds all the products which name contains the given value
     * @param column the columns name we want to search in, in this case the name of the product
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
     * Given a Product which exists in the DB, it updates it's details in the DB
     * @param value it's the given T type object (in this case Product)
     * @return the number of rows affected by the update
     * @see DBConnection executeQuery() method
     */
    @Override
    public int update(Product value) {
        return 0;
    }

    /**
     * This method takes an object and converts it to a valid SQL DELETE query, which is the executed
     * Given a product which exists in the DB, it deletes it from the DB
     * @param value it's the given T type object (in this case Product)
     * @return the number of rows deleted from the table (1 or 0)
     * @see DBConnection executeQuery()
     */
    @Override
    public int delete(Product value) {
        return 0;
    }
}
