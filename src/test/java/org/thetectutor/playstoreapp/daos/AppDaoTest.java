package org.thetectutor.playstoreapp.daos;

public class AppDaoTest {
    public static void main(String[] args) {
        /*App app = new App();
        app.setAppName("Paypal");
        app.setCategory("Finance");
        app.setAgeLimit(4);
        String dateString = "2023-5-1";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        app.setDate(localDate);
        app.setVersion(8.39);
        app.setRating(4.8);
        app.setDescription("PayPal is a mobile payment app that allows users to send, receive, and manage money securely and conveniently.");*/


        AppDAO appDAO = new AppDAO();
        System.out.println(appDAO.updateAppVersion(20,20));
        //System.out.println(appDAO.insertApp(app));
        /*List<App> appList = appDAO.getAllApps();
        Map<String, List<String>> categoryMap = new HashMap<>();
        for (App apps : appList) {
            categoryMap.computeIfAbsent(apps.getCategory(), k -> new ArrayList<>());
            categoryMap.get(apps.getCategory()).add(apps.getAppName());
        }
        for(Map.Entry<String, List<String>> sublist : categoryMap.entrySet()){
            System.out.println(sublist);
        }*/
       /* appDAO.deleteApp(12);
        System.out.println(appDAO.getAllApps());*/
        //System.out.println(appDAO.getAppById(12));
    }
}
