package com.example.uvgmeet;
public class User {
    private String username;
    private int age;
    private String university;
    private String interests;

    // Constructor
    public User(String username, int age, String university, String interests) {
        this.username = username;
        this.age = age;
        this.university = university;
        this.interests = interests;
    }

    // Métodos getter y setter para los atributos del usuario
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", university='" + university + '\'' +
                ", interests='" + interests + '\'' +
                '}';
    }
}
