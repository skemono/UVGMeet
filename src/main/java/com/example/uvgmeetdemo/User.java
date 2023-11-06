package com.example.uvgmeetdemo;
public class User {
    private String username;
    private String name;
    private int age;
    private String university;
    private String interests;


    public User(String username, String name, int age, String university, String interests) {
        this.username = username;
        this.name = name;
        this.age = age;
        this.university = university;
        this.interests = interests;
    }

    public User(String text, String text1) {
        this.username = "username";
        this.name = "name";
        this.age = 1;
        this.university = "university";
        this.interests = "interests";
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


}
