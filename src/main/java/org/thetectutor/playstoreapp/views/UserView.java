package org.thetectutor.playstoreapp.views;

import java.util.Scanner;

public class UserView {
    private Scanner scanner;

    public UserView() {
        scanner = new Scanner(System.in);
    }

    public void displayWelcomeMessage() {
        System.out.println("Play Store APP");
    }

    public void displayMenu() {
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    public int getMenuChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public void closeScanner() {
        scanner.close();
    }

    public boolean isScannerClosed() {
        return !scanner.hasNextLine();
    }
}
