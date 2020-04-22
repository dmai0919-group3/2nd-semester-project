package database;

import model.Product;

import java.sql.ResultSet;

public class ProductDB implements DBInterface<Product> {

    /**
     * This method takes an object and converts it to a valid SQL INSERT query, which is the executed
     * Given a valid product which doesn't exist in the database, it inserts it to the DB
     * @param value it's the given T type object (in this case Product)
     * @return the generated key after the insertion to the DB
     * @see DBConnection executeInsertWithID() method
     */
    @Override
    public int create(Product value) {
        return 0;
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
