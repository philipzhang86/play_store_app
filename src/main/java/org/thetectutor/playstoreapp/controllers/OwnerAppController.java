package org.thetectutor.playstoreapp.controllers;

import org.thetectutor.playstoreapp.models.App;
import org.thetectutor.playstoreapp.services.OwnerAppServiceImpl;
import org.thetectutor.playstoreapp.views.OwnerAppView;

import java.util.Scanner;

public class OwnerAppController {
    private OwnerAppServiceImpl ownerAppService;
    private OwnerAppView ownerAppView;
    private Scanner scanner;

    public OwnerAppController() {
        ownerAppService = new OwnerAppServiceImpl();
        ownerAppView = new OwnerAppView();
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean isRunning = true;
        ownerAppView.displayWelcomeMessage();

        while (isRunning) {
            ownerAppView.displayMenu();
            int choice = ownerAppView.getMenuChoice(scanner);

            switch (choice) {
                case 1:
                    App app = ownerAppView.getAppDetails(scanner);
                    ownerAppService.insertApp(app);
                    break;
                case 2:
                    int id = ownerAppView.getAppId(scanner);
                    int age = ownerAppView.getAgeLimit(scanner);
                    ownerAppService.updateAppMinAge(id, age);
                    break;
                case 3:
                    id = ownerAppView.getAppId(scanner);
                    double version = ownerAppView.getVersion(scanner);
                    ownerAppService.updateAppVersion(id, version);
                    break;
                case 4:
                    id = ownerAppView.getAppId(scanner);
                    ownerAppService.deleteApp(id);
                    break;
                case 5:
                    ownerAppView.displayAppList(ownerAppService.getAllApps());
                    break;
                case 6:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
