package org.thetectutor.playstoreapp.models;

public class UserTest {
    public static void main(String[] args) {
        User u1 = new User();
        u1.setId(1);
        u1.setUserName("Joyce");
        u1.setPassword("123456");
        u1.setType(UserType.OWNER);
        System.out.println(u1);
    }
}
