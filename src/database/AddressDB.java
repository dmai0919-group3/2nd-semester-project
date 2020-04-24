package database;

import model.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressDB implements DBInterface<Address> {
    DBConnection db = DBConnection.getInstance();

    private static String tableName = "Address";

    /**
     * This method takes an object and converts it to a valid SQL INSERT query, which is the executed
     * Given a valid Address which doesn't exist in the database, it inserts it to the DB
     * @param value it's the given T type object (in this case Address)
     * @return the generated key after the insertion to the DB
     * @see DBConnection executeInsertWithID() method
     */
    @Override
    public int create(Address value) {
        String query = "INSERT INTO '?' ('number', 'supplement', 'street', 'city', 'zipcode', 'region', 'country') VALUES (?, ? ,? , ?, ?, ?, ?);";

        int resultID = null;
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(query);

            s.setString(1, ProductDB.tableName);
            s.setString(2, value.getNumber());
            s.setString(3, value.getSupplement());
            s.setString(4, value.getStreet());
            s.setString(5, value.getCity());
            s.setString(6, value.getZipcode());
            s.setString(7, value.getRegion());
            s.setString(8, value.getCountry());
            resultID = db.executeInsertWithID(s.toString());

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultID;
    }

    /**
     * This method takes an ID and converts it to a valid SQL SELECT query, which is the executed
     * Given an ID this method returns a single Address which has the given ID
     * @param id is the ID which we want to search for in the database
     * @return the single object with the given ID
     * @see DBConnection executeSelect() method
     */
    @Override
    public Address selectByID(int id) {
        String query = "SELECT TOP 1 * FROM '?' WHERE id=?;";
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(query);

            s.setString(1, ProductDB.tableName);
            s.setString(2, id);

            ResultSet rs = db.executeSelect(s.toString());
            if (rs.next()) {
                return new Address(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("weight"),
                        rs.getDouble("price"),
                        rs.getInt("minQuantity"));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    /**
     * This method takes a column name and a search value, converts it to a valid SQL SELECT query, which is the executed
     * Given a column (Address name) and a value it finds all the products which name contains the given value
     * @param column the columns name we want to search in, in this case the name of the Address
     * @param value  the value we want to search for
     * @return the ResultSet containing all the results of the query, null if there's an error and an empty ResultSet if there are 0 results
     * @see DBConnection executeSelect() method
     */
    @Override
    public ResultSet selectByString(String column, String value) {
        String query = "SELECT * FROM '?' WHERE ?=?;";
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(query);

            s.setString(1, ProductDB.tableName);
            s.setString(2, column);
            s.setString(3, value);

            return db.executeSelect(s.toString());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    /**
     * This method takes an object and converts it to a valid SQL UPDATE query, which is the executed
     * Given a Address which exists in the DB, it updates it's details in the DB
     * @param value it's the given T type object (in this case Address)
     * @return the number of rows affected by the update
     * @see DBConnection executeQuery() method
     */
    @Override
    public int update(Address value) {
        String query= "UPDATE '?' SET (number=?, supplement=?, street=?, city=?, zipcode=?, region=?, country=?) WHERE id=?;"
        int rows = -1;
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(query);

            s.setString(1, ProductDB.tableName);
            s.setString(2, value.getNumber());
            s.setString(3, value.getSupplement());
            s.setString(4, value.getStreet());
            s.setString(5, value.getCity());
            s.setString(6, value.getZipcode());
            s.setString(7, value.getRegion());
            s.setString(8, value.getCountry());
            s.setString(9, value.getId());

            rows = db.executeQuery(s.toString());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rows;
    }

    /**
     * This method takes an object and converts it to a valid SQL DELETE query, which is the executed
     * Given a Address which exists in the DB, it deletes it from the DB
     * @param value it's the given T type object (in this case Address)
     * @return the number of rows deleted from the table (1 or 0)
     * @see DBConnection executeQuery()
     */
    @Override
    public int delete(Address value) {
        // Because of the tables cascade rule, we dont need to have separate stock query
        String query = "DELETE FROM '?' WHERE id=?";
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(queryProd);

            s.setString(1, ProductDB.tableName);
            s.setString(2, value.getId());

            return db.executeQuery(s.toString());
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        }
        return -1;
    }
}
