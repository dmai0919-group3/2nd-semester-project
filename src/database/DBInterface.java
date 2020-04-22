package database;

import java.sql.ResultSet;

/**
 * This interface is used in connection with the DAO pattern
 * @param <T> with this parameter you can set a type which you will use in later methods as parameters
 */
public interface DBInterface<T> {

    /**
     * This method takes an object and converts it to a valid SQL INSERT query, which is the executed
     * @param value it's the given T type object
     * @return the generated key after the insertion to the DB
     * @see DBConnection executeInsertWithID() method
     */
    public int create(T value);

    /**
     * This method takes an ID and converts it to a valid SQL SELECT query, which is the executed
     * @param id is the ID which we want to search for in the database
     * @return the single object with the given ID
     * @see DBConnection executeSelect() method
     */
    public T selectByID(int id);

    /**
     * This method takes a column name and a search value, converts it to a valid SQL SELECT query, which is the executed
     * @param column the columns name we want to search in
     * @param value the value we want to search for
     * @return the ResultSet containing all the results of the query
     * @see DBConnection executeSelect() method
     */
    public ResultSet selectByString(String column, String value);

    /**
     * This method takes an object and converts it to a valid SQL UPDATE query, which is the executed
     * @param value it's the given T type object
     * @return the number of rows affected by the update
     * @see DBConnection executeQuery() method
     */
    public int update(T value);

    /**
     * This method takes an object and converts it to a valid SQL DELETE query, which is the executed
     * @param value it's the given T type object
     * @return the number of rows deleted from the table
     * @see DBConnection executeQuery()
     */
    public int delete(T value);
}
