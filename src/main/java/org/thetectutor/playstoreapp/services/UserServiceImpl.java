package org.thetectutor.playstoreapp.services;

import org.thetectutor.playstoreapp.daos.UserDAO;
import org.thetectutor.playstoreapp.models.User;
import org.thetectutor.playstoreapp.utils.UserAuthException;
import org.thetectutor.playstoreapp.utils.DuplicateEntryException;

public class UserServiceImpl implements UserServices {
    private UserDAO userDAO;

    public UserServiceImpl() {
        userDAO = new UserDAO();
    }

    @Override
    public boolean registerUser(User user) {
        try {
            return userDAO.insertUser(user);
        } catch (DuplicateEntryException e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
            return false;
        }
    }

    @Override
    public User login(String username, String password) throws UserAuthException {
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        User authUser = userDAO.verifyUser(user);
        if (authUser == null) {
            throw new UserAuthException("Invalid username or password");
        }
        return authUser;
    }
}
