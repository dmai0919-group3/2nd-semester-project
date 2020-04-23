package database;

import model.Address;

import java.sql.ResultSet;

public class AddressDB implements DBInterface<Address> { // TODO METHODS NOT IMPLEMENTED!!!

    /**
     * This method takes an object and converts it to a valid SQL INSERT query, which is the executed
     *
     * @param value it's the given T type object
     * @return the generated key after the insertion to the DB
     * @see DBConnection executeInsertWithID() method
     */
    @Override
    public int create(Address value) {
        return 0;
    }

    /**
     * This method takes an ID and converts it to a valid SQL SELECT query, which is the executed
     *
     * @param id is the ID which we want to search for in the database
     * @return the single object with the given ID
     * @see DBConnection executeSelect() method
     */
    @Override
    public Address selectByID(int id) {
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
    public int update(Address value) {
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
    public int delete(Address value) {
        return 0;
    }
}
