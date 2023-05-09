package org.thetectutor.playstoreapp.models;

import java.time.LocalDate;

public class App {
    private int id;
    private String appName;
    private String category;
    private int ageLimit;
    private LocalDate releaseDate;
    private double version;
    private double rating;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String name) {
        this.appName = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int age) {
        this.ageLimit = age;
    }

    public LocalDate getDate() {
        return releaseDate;
    }

    public void setDate(LocalDate date) {
        this.releaseDate = date;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "App id=" + id +
                ", appName='" + appName + '\'' +
                ", category='" + category + '\'' +
                ", age=" + ageLimit + "+"+
                ", releaseDate=" + releaseDate +
                ", version=" + version +
                ", rating=" + rating +
                "\nDescription: '" + description + '\'';
    }
}
