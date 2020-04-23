package controller;

import model.User;

public class LoginController {
    private static User loggedInUser;

    public static boolean logIn(String username, String password) {
        // TODO: User login logic
        return true;
    }

    public static boolean logOut() {
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
