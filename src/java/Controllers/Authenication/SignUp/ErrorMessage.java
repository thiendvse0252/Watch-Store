package Controllers.Authenication.SignUp;

import java.sql.SQLException;
import javax.naming.NamingException;
import sample.user.UserDAO;
import sample.user.UserDTO;

public class ErrorMessage {

    public static String checkUsernameExist(String username) throws NamingException {
        String error = null;
        UserDTO user = null;
        try {
            user = UserDAO.getUserUsername(username);
        } catch (SQLException ex) {
            System.out.println("SQLException - ErrorMessage");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException - ErrorMessage");
        }
        if (user != null) {
            error = username + " has been existed. ";
        }
        return error;
    }

    public static String checkUsernameLength(String username) {
        String error = null;
        if (username.isEmpty()) {
            error = "Please type your username.";
        } else if (username.length() < 6) {
            error = username + " is too short (at least 6 characters).";
        } else if (username.length() > 20) {
            error = username + " is too long (at most 20 characters)";
        }
        return error;

    }

    public static String checkPasswordLength(String password) {
        String error = null;
        if (password.isEmpty()) {
            error = "Please type your password.";
        } else if (password.length() < 6) {
            error = password + " is too short (at least 6 characters).";
        } else if (password.length() > 20) {
            error = password + " is too long (at most 20 characters)";
        }
        return error;

    }

    public static String checkPasswordConfirm(String password, String confirm) {
        String error = null;
        if (confirm.isEmpty()) {
            error = "Please type confirm.";
        } else if (!password.equalsIgnoreCase(confirm)) {
            error = "Your password and your confirm are not match.";
        }
        return error;
    }

    public static String checkFirstNameLength(String firstName) {
        String error = null;
        if (firstName.isEmpty()) {
            error = "Please type your first name.";
        } else if (firstName.length() < 4) {
            error = firstName + " is too short (at least 4 characters).";
        } else if (firstName.length() > 30) {
            error = firstName + " is too long (at most 30 characters)";
        }
        return error;
    }

    public static String checkLastNameLength(String lastName) {
        String error = null;
        if (lastName.isEmpty()) {
            error = "Please type your last name.";
        } else if (lastName.length() < 4) {
            error = lastName + " is too short (at least 4 characters).";
        } else if (lastName.length() > 30) {
            error = lastName + " is too long (at most 30 characters)";
        }
        return error;
    }

    public static String checkTerm(String term) {
        String error = null;
        if (term == null) {
            error = "Please check the box before sign up.";
        }
        return error;
    }
}
