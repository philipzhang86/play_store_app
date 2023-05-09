package org.thetectutor.playstoreapp.services;

import org.thetectutor.playstoreapp.daos.AppDAO;
import org.thetectutor.playstoreapp.models.App;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAppServiceImpl implements UserAppService {
    private AppDAO appDAO;
    private List<App> appList;
    private int age;

    public UserAppServiceImpl(int age) {
        appDAO = new AppDAO();
        this.age = age;
        appList = getAppByAge();
    }

    public List<App> getAppList() {
        return appList;
    }

    @Override
    public App searchAppByName(String name) {
        for (App app : appList) {
            if (app.getAppName().equals(name)) return app;
        }
        return null;
    }

    @Override
    public Map<String, List<String>> showAppByCategory() {
        Map<String, List<String>> categoryMap = new HashMap<>();
        for (App apps : appList) {
            categoryMap.computeIfAbsent(apps.getCategory(), k -> new ArrayList<>());
            categoryMap.get(apps.getCategory()).add(apps.getAppName());
        }
        return categoryMap;
    }

    @Override
    public App selectAppByList(int choice) {
        int size = appList.size();
        if (choice < 0 || choice >= size) return null;
        return appList.get(choice);
    }

    private List<App> getAppByAge() {
        List<App> originalList = appDAO.getAllApps();
        appList = new ArrayList<>();
        for (App app : originalList) {
            if (app.getAgeLimit() <= age) {
                appList.add(app);
            }
        }
        return appList;
    }
}
