package database;

import java.sql.*;
import java.util.Scanner;

public class DBConnection {
    private static Connection connection;
    private static DBConnection instance;

    /**
     * Private constructor which check the environmental variable 'TEVOS_JDBC_URL' for the JDBC url, and if it's not set
     * it will ask the user to enter the URL manually to the console.
     */
    private DBConnection() {
        // Check if the environmental variable TEVOS_JDBC_URL is set. If it's not, read the JDBC URL one-by-one from the console
        // if it is set, read the URL from there
        String jdbc_url = "jdbc:sqlserver://";
        if (System.getenv("TEVOS_JDBC_URL") == null) {
            System.out.println("DB URL: ");
            Scanner in = new Scanner(System.in);
            jdbc_url  += in.nextLine() + ";databaseName=";
            System.out.println("DB NAME: ");
            jdbc_url += in.nextLine() + ";user=";
            System.out.println("DB USER: ");
            jdbc_url += in.nextLine() + ";password=";
            System.out.println("DB PASS: ");
            jdbc_url += in.nextLine() + ";";
        } else {
            jdbc_url = System.getenv("TEVOS_JDBC_URL");
        }
        try {
            connection = DriverManager.getConnection(jdbc_url);
            connection.setAutoCommit(true);
            DatabaseMetaData dma = connection.getMetaData();
//            System.out.println("Connected to: " + dma.getURL());
//            System.out.println("Driver: " + dma.getDriverName());
//            System.out.println("Database product name: " + dma.getDatabaseProductName());
            System.out.println("Successfully connected to the database.");
        } catch(Exception e) {
            System.err.println("Problems with the connection to the database: ");
            System.err.println(e.getMessage());
            System.err.println("Database URL: ****");
        }
    }

    /**
     * According to the singleton pattern, we have a private constructor and a public getInstance method which makes
     * sure there is only one instance of the DBConnection class at runtime.
     * @return It returns the single available instance object of the DBConnection class
     */
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    /**
     * Closes the connection and deletes the instance (sets it to null)
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instance = null;
        System.out.println("Connection was closed successfully.");
    }

    public Connection getDBConn() {
        return connection;
    }

    public Boolean instanceIsNull() {
        return (instance == null);
    }

    // HERE STARTS THE QUERY EXECUTION METHODS

    /**
     * Executes a given select query on the database
     * @param query is a valid SQL statement
     * @return It returns a ResultSet containing the query's result
     * @throws SQLException when there is a problem connection to the database or the given query is not valid
     */
    public ResultSet executeSelect(String query) throws SQLException {
        ResultSet rs;
        Statement s = connection.createStatement();

        rs = s.executeQuery(query);
        s.close();

        return rs;
    }

    /**
     * Executes a given update or delete query on the database
     * @param query is a valid SQL statement
     * @return It returns the number of rows affected by the given query
     * @throws SQLException when there is a problem connection to the database or the given query is not valid
     */
    public int executeQuery(String query) throws SQLException {
        int result;
        Statement s = connection.createStatement();

        result = s.executeUpdate(query);
        s.close();

        return result;
    }

    /**
     * Executes a given insert query on the database
     * @param query is a valid SQL statement
     * @return It returns the generated key of the inserted row
     * @throws SQLException when there is a problem connection to the database or the given query is not valid
     */
    public int executeInsertWithID(String query) throws SQLException {
        ResultSet rs;
        int generatedKey = -1;
        Statement s = connection.createStatement();

        s.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        rs = s.getGeneratedKeys();
        if (rs.next()) {
            generatedKey = rs.getInt(1);
        }
        s.close();

        return generatedKey;
    }
}
