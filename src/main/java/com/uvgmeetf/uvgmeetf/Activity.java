package com.uvgmeetf.uvgmeetf;

import java.time.LocalDate;

public class Activity {
    private String name;
    private String description;
    private LocalDate date;
    private String location;

    public Activity(String name, String description, LocalDate date, String location) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
    }


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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
        return "Actividad: " + name + "\n" +
                "Descripción: " + description + "\n" +
                "Fecha: " + date + "\n" +
                "Ubicación: " + location;
    }
}
