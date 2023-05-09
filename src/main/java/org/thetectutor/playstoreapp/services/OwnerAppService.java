package org.thetectutor.playstoreapp.services;

import org.thetectutor.playstoreapp.models.App;

import java.util.List;

public interface OwnerAppService {
    boolean insertApp(App app);

    boolean updateAppMinAge(int id, int age);

    boolean updateAppVersion(int id, double version);

    boolean deleteApp(int id);

    List<App> getAllApps();

    App getAppById(int id);

    App getByName(String name);
}
