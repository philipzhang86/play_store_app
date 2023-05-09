package org.thetectutor.playstoreapp.controllers;

import org.thetectutor.playstoreapp.models.App;
import org.thetectutor.playstoreapp.services.UserAppServiceImpl;
import org.thetectutor.playstoreapp.views.UserAppView;

import java.util.Scanner;

public class UserAppController {
    private UserAppServiceImpl userAppService;
    private UserAppView userAppView;
    private Scanner scanner;

    public UserAppController(int age) {
        userAppService = new UserAppServiceImpl(age);
        userAppView = new UserAppView();
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        userAppView.displayWelcomeMessage();
        while (!exit) {

            userAppView.displayMenu();
            int choice = userAppView.getMenuChoice();

            switch (choice) {
                case 1:
                    searchAppByName();
                    break;
                case 2:
                    showAppByCategory();
                    break;
                case 3:
                    selectAppByList();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void searchAppByName() {
        System.out.print("Enter app name: ");
        String appName = scanner.nextLine();
        App app = userAppService.searchAppByName(appName);
        if (app != null) {
            userAppView.displayApp(app);
        } else {
            System.out.println("App not found.");
        }
    }

    private void showAppByCategory() {
        userAppView.displayAppCategories(userAppService.showAppByCategory());
    }

    private void selectAppByList() {
        userAppView.displayAppList(userAppService.getAppList());
        System.out.print("Enter the index of the app you want to view: ");
        int choice = scanner.nextInt() ;
        App app = userAppService.selectAppByList(choice - 1);
        if (app != null) {
            userAppView.displayApp(app);
        } else {
            System.out.println("Invalid index.");
        }
    }
}
