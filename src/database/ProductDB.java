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
    public int create(Product value) throws DataAccessException {
        String queryProd = "INSERT INTO 'Product' ('name', 'weight', 'price', 'minQuantity') VALUES (?, ? ,? , ?);";
        int productID = -1;
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(queryProd);
            s.setString(1, value.getName()); // name
            s.setInt(2, value.getWeight()); // weight
            s.setDouble(3, value.getPrice()); // price
            productID = db.executeInsertWithID(s.toString());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
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
    public Product selectByID(int id) throws DataAccessException {
        String query = "SELECT TOP 1 * FROM 'Product' WHERE id=?;";
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(query);
            s.setInt(1, id);
            ResultSet rs = db.executeSelect(s.toString());
            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("weight"),
                        rs.getDouble("price"));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
        }
        return null;
    }

    /**
     * This method takes a column name and a search value, converts it to a valid SQL SELECT query, which is the executed
     * Given a column (product name) and a value it finds all the products which name contains the given value
     * @param column the columns name we want to search in, in this case the name of the product
     * @param value  the value we want to search for
     * @return the ResultSet containing all the results of the query, null if there's an error and an empty ResultSet if there are 0 results
     * @see DBConnection executeSelect() method
     */
    @Override
    public ResultSet selectByString(String column, String value) {
        String query = "SELECT * FROM 'Product' WHERE ?=?;";
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

    /**
     * This method takes an object and converts it to a valid SQL UPDATE query, which is the executed
     * Given a Product which exists in the DB, it updates it's details in the DB
     * @param value it's the given T type object (in this case Product)
     * @return the number of rows affected by the update
     * @see DBConnection executeQuery() method
     */
    @Override
    public int update(Product value) throws DataAccessException {
        String queryProduct = "UPDATE 'Product' SET (name=?, weight=?, price=?, minQuantity=?) WHERE id=" + value.getId() + ";";
        int rows = -1;
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(queryProduct);
            s.setString(1, value.getName());
            s.setInt(2, value.getWeight());
            s.setDouble(3, value.getPrice());
            rows = db.executeQuery(s.toString());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
        }
        return rows;
    }

    /**
     * This method takes an object and converts it to a valid SQL DELETE query, which is the executed
     * Given a product which exists in the DB, it deletes it from the DB
     * @param value it's the given T type object (in this case Product)
     * @return the number of rows deleted from the table (1 or 0)
     * @see DBConnection executeQuery()
     */
    @Override
    public int delete(Product value) throws DataAccessException {
        // Because of the tables cascade rule, we dont need to have separate stock query
        String query = "DELETE FROM 'Product' WHERE id=?";
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(query);
            s.setInt(1, value.getId());
            return db.executeQuery(s.toString());
        } catch (SQLException e) {
             System.err.println(e.getMessage());
            throw new DataAccessException();
        }
    }
}
