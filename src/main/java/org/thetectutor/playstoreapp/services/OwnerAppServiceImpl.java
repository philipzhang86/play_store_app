package org.thetectutor.playstoreapp.services;

import org.thetectutor.playstoreapp.daos.AppDAO;
import org.thetectutor.playstoreapp.models.App;
import org.thetectutor.playstoreapp.utils.DuplicateEntryException;

import java.util.List;

public class OwnerAppServiceImpl implements OwnerAppService {
    private AppDAO appDAO;

    public OwnerAppServiceImpl() {
        appDAO = new AppDAO();
    }

    @Override
    public boolean insertApp(App app) {
        try {
            return appDAO.insertApp(app);
        } catch (DuplicateEntryException e) {
            System.out.println(e.getMessage() + " Please enter another app");
            return false;
        }
    }

    @Override
    public boolean updateAppMinAge(int id, int age) {
        return appDAO.updateAppAgeLimit(id, age);
    }

    @Override
    public boolean updateAppVersion(int id, double version) {
        return appDAO.updateAppVersion(id, version);
    }

    @Override
    public boolean deleteApp(int id) {
        return appDAO.deleteApp(id);
    }

    @Override
    public List<App> getAllApps() {
        return appDAO.getAllApps();
    }

    @Override
    public App getAppById(int id) {
        return appDAO.getAppById(id);
    }

    @Override
    public App getByName(String name) {
        return appDAO.getByName(name);
    }
}
