package org.thetectutor.playstoreapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private static DBConnect instance;
    private static Connection con;

    private DBConnect() {
        try {
            //com.mysql.cj.jdbc.Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/play_store_app_info", "root", "123456");

            System.out.println("Data connected");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Connection getConnect() {
        if (instance == null) {
            instance = new DBConnect();
        }
        return instance.con;
    }

    public static void main(String[] args) {
        getConnect();
    }
}
