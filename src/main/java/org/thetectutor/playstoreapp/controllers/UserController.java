package org.thetectutor.playstoreapp.controllers;

import org.thetectutor.playstoreapp.models.User;
import org.thetectutor.playstoreapp.models.UserType;
import org.thetectutor.playstoreapp.services.UserServiceImpl;
import org.thetectutor.playstoreapp.utils.UserAuthException;
import org.thetectutor.playstoreapp.views.UserView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserController {
    private UserServiceImpl userService;
    private UserView userView;
    private String ownerRegistrationPassword = "admin"; // You could set password base on your need
    private Scanner scanner;

    public UserController() {
        userService = new UserServiceImpl();
        userView = new UserView();
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;

        while (!exit) {
            userView.displayWelcomeMessage();
            userView.displayMenu();
            int choice = userView.getMenuChoice();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        userView.closeScanner();
    }

    private void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        UserServiceImpl userService = new UserServiceImpl();
        try {
            User loggedInUser = userService.login(username, password);
            System.out.println("Login successful!");
            System.out.println("Welcome, " + loggedInUser.getUserName());

            Thread userThread = new Thread(() -> {
                if (loggedInUser.getType() == UserType.USER) {
                    UserAppController userAppControl = new UserAppController(loggedInUser.getAge());
                    userAppControl.start();
                } else {
                    OwnerAppController ownerAppControl = new OwnerAppController();
                    ownerAppControl.start();
                }
            });

            userThread.start();

            // Block main thread，until userThread finish execution/dead
            userThread.join();//after userThread has been terminated, main thread continue
        } catch (UserAuthException e) {
            System.out.println("Login failed: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Thread execution was interrupted: " + e.getMessage());
        }
    }

    private void register() {
        System.out.print("Enter username: ");
        String regUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String regPassword = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = -1;
        while (age == -1) {
            System.out.print("Enter age: ");
            try {
                age = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }
        scanner.nextLine(); // Consume the newline character after the integer

        UserType userType = null;
        while (userType == null) {
            System.out.println("Select user type:");
            System.out.println("1. User");
            System.out.println("2. Owner");
            System.out.print("Enter your choice: ");
            int userTypeChoice = scanner.nextInt();
            scanner.nextLine();

            if (userTypeChoice == 1) {
                userType = UserType.USER;
            } else if (userTypeChoice == 2) {
                System.out.print("Enter owner registration password: ");
                String ownerPassword = scanner.nextLine();
                if (!ownerPassword.equals(ownerRegistrationPassword)) {
                    System.out.println("Invalid owner registration password!");
                } else {
                    userType = UserType.OWNER;
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        User newUser = new User();
        newUser.setUserName(regUsername);
        newUser.setPassword(regPassword);
        newUser.setAge(age);
        newUser.setType(userType);

        try {
            if (userService.registerUser(newUser)) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("Registration failed!");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        }
    }

}
