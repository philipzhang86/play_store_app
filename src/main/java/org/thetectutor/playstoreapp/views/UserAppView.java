package org.thetectutor.playstoreapp.views;

import org.thetectutor.playstoreapp.models.App;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserAppView {
    private Scanner scanner;

    public UserAppView() {
        scanner = new Scanner(System.in);
    }

    public void displayWelcomeMessage() {
        System.out.println("Welcome to the User App section!");
    }

    public void displayMenu() {
        System.out.println("\n1. Search app by name");
        System.out.println("2. Show apps by category");
        System.out.println("3. Select app by list");
        System.out.println("4. Exit");
    }

    public int getMenuChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public String getAppName() {
        System.out.print("Enter the app name: ");
        scanner.nextLine(); // Consume the newline character
        return scanner.nextLine();
    }

    public void displayApp(App app) {
        System.out.println(app);
    }

    public void displayAppCategories(Map<String, List<String>> categoryMap) {
        for (Map.Entry<String, List<String>> entry : categoryMap.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (String appName : entry.getValue()) {
                System.out.println(" - " + appName);
            }
            System.out.println();
        }
    }

    public int getSelectedAppIndex() {
        System.out.print("Enter the app index: ");
        return scanner.nextInt();
    }

    public void displayAppList(List<App> apps) {
        for (int i = 0; i < apps.size(); i++) {
            System.out.println((i + 1) + ". " + apps.get(i).getAppName());
        }
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
