package org.thetectutor.playstoreapp.views;

import org.thetectutor.playstoreapp.models.App;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class OwnerAppView {

    public void displayWelcomeMessage() {
        System.out.println("Welcome to the Owner App section!");
    }

    public void displayMenu() {
        System.out.println("\n1. Add a new app");
        System.out.println("2. Update app minimum age");
        System.out.println("3. Update app version");
        System.out.println("4. Delete an app");
        System.out.println("5. Show all apps");
        System.out.println("6. Exit");
    }

    public int getMenuChoice(Scanner scanner) {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public App getAppDetails(Scanner scanner) {
        App app = new App();

        System.out.print("Enter app name: ");
        scanner.nextLine(); // Consume the newline character
        app.setAppName(scanner.nextLine());

        System.out.print("Enter category: ");
        app.setCategory(scanner.nextLine());

        System.out.print("Enter minimum age: ");
        app.setAgeLimit(scanner.nextInt());
        scanner.nextLine(); // Add this line to consume the newline character

        System.out.println("Enter release date (Format: yyyy-M-d):"); // Update the prompt
        String dateString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        app.setDate(localDate);

        System.out.print("Enter version: ");
        app.setVersion(scanner.nextDouble());

        System.out.print("Enter rating: ");
        app.setRating(scanner.nextDouble());

        System.out.print("Enter description: ");
        scanner.nextLine(); // Consume the newline character
        app.setDescription(scanner.nextLine());

        return app;
    }

    public int getAppId(Scanner scanner) {
        System.out.print("Enter app ID: ");
        return scanner.nextInt();
    }

    public int getAgeLimit(Scanner scanner) {
        System.out.print("Enter new minimum age: ");
        return scanner.nextInt();
    }

    public double getVersion(Scanner scanner) {
        System.out.print("Enter new version: ");
        return scanner.nextDouble();
    }

    public void displayAppList(List<App> apps) {
        for (App app : apps) {
            System.out.println(app);
        }
    }
}
