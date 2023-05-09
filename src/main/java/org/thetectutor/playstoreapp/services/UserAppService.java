package org.thetectutor.playstoreapp.services;

import org.thetectutor.playstoreapp.models.App;

import java.util.List;
import java.util.Map;

public interface UserAppService {
    App searchAppByName(String name);

    Map<String, List<String>> showAppByCategory();

    App selectAppByList(int choice);
}
