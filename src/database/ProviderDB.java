package database;

import model.Provider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProviderDB implements DBInterface<Provider>{ // TODO METHODS NOT IMPLEMENTED!!!
    DBConnection db = DBConnection.getInstance();

    /**
     * This method takes an object and converts it to a valid SQL INSERT query, which is the executed
     *
     * @param value it's the given T type object
     * @return the generated key after the insertion to the DB
     * @see DBConnection executeInsertWithID() method
     */
    @Override
    public int create(Provider value) throws DataAccessException {
        String query = "INSERT INTO 'Provider' ('name', 'email','available', 'addressID') VALUES (?, ?, ?, ?)";
        AddressDB addressDB = new AddressDB();
        int addressID = addressDB.create(value.getAddress());
        int providerID = -1;
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(query);
            s.setString(1, value.getName());
            s.setString(2, value.getEmail());
            s.setBoolean(3, value.getAvailable());
            s.setInt(4, addressID);
            providerID = db.executeInsertWithID(s.toString());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
        }
        return providerID;
    }

    /**
     * This method takes an ID and converts it to a valid SQL SELECT query, which is the executed
     *
     * @param id is the ID which we want to search for in the database
     * @return the single object with the given ID
     * @see DBConnection executeSelect() method
     */
    @Override
    public Provider selectByID(int id) throws DataAccessException {
        String query = "SELECT TOP 1 * FROM 'Provider' WHERE id=?;";
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(query);
            s.setInt(1, id);
            ResultSet rs = db.executeSelect(s.toString());
            AddressDB addressDB = new AddressDB();
            if (rs.next()) {
                return new Provider(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getBoolean("available"),
                        addressDB.selectByID(rs.getInt("addressID")));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DataAccessException();
        }
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
    public ResultSet selectByString(String column, String value) throws DataAccessException {
        String query = "SELECT * FROM 'Provider' WHERE ?=?";
        ResultSet rs;
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(query);
            s.setString(1, column);
            s.setString(2, value);
            return db.executeSelect(s.toString());
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
    public int update(Provider value) throws DataAccessException {
        String queryProduct = "UPDATE 'Provider' SET (name=?, email=?, available=?) WHERE id=" + value.getId() + ";";
        int rows = -1;
        try {
            PreparedStatement s = db.getDBConn().prepareStatement(queryProduct);
            s.setString(1, value.getName());
            s.setString(2, value.getEmail());
            s.setBoolean(3, value.getAvailable());
            rows = db.executeQuery(s.toString());
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
    public int delete(Provider value) throws DataAccessException {
        String query = "DELETE FROM 'Provider' WHERE id=?";
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
