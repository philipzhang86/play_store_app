package org.thetectutor.playstoreapp;

import org.thetectutor.playstoreapp.controllers.UserController;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserController uc = new UserController();
        uc.start();
    }
}
