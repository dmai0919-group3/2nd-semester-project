package controller;

import database.AddressDB;
import database.DataAccessException;
import database.UserDB;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private static User loggedInUser;
    private UserDB userDB;

    public LoginController() {
        userDB = new UserDB();
    }

    public boolean logIn(String username, String password) throws DataAccessException, ControlException {
        ResultSet users = userDB.selectByString("name" ,username);

        try {
            while (users.next()) {
                if (users.getString("password").equals(password)) {
                    AddressDB addressDB = new AddressDB();
                    loggedInUser = new User(
                            users.getInt("id"),
                            users.getString("name"),
                            users.getString("password"),
                            users.getString("email"),
                            addressDB.selectByID(users.getInt("addressID"))
                    );
                    return true;
                }
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public boolean logOut() {
        if (loggedInUser != null) {
            loggedInUser = null;
            return true;
        } else {
            return false;
        }
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }
}
