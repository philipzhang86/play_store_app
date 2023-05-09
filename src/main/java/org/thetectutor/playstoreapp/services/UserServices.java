package org.thetectutor.playstoreapp.services;

import org.thetectutor.playstoreapp.models.User;

public interface UserServices {
    boolean registerUser(User user);

    User login(String username, String password);
}
