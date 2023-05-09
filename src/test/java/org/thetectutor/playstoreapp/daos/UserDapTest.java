package org.thetectutor.playstoreapp.daos;

import org.thetectutor.playstoreapp.models.User;
import org.thetectutor.playstoreapp.models.UserType;

public class UserDapTest {
    public static void main(String[] args) {
        User u1 = new User();
        u1.setUserName("Joyce");
        u1.setPassword("123456");
        u1.setAge(25);
        u1.setType(UserType.OWNER);
        UserDAO userDAO = new UserDAO();
        System.out.println(userDAO.insertUser(u1));
    }
}
