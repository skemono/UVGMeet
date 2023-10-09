package com.example.uvgmeet;

import java.util.Date;

public class Activity {
    private String name;
    private String description;
    private Date date;
    private String location;

    // Constructor
    public Activity(String name, String description, String date, String location) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
    }

    // Métodos getter y setter para los atributos de la actividad
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                '}';
    }
}
