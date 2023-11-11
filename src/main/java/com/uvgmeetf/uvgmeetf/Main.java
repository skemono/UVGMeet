package com.example.uvgmeetdemo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;


        LoginScreen loginScreen = new LoginScreen();
        ProfileCreationScreen profileCreationScreen = new ProfileCreationScreen();
        MatchingScreen matchingScreen = new MatchingScreen();
        ProfileScreen profileScreen = new ProfileScreen(new User("Pedro", "ola"));
        InterestedScreen interestedScreen = new InterestedScreen();
        ActivitiesScreen activitiesScreen = new ActivitiesScreen();
        Scene loginScene = new Scene(loginScreen.getPane(), 800, 600);
        primaryStage.setTitle("UVGMeet DEMO");
        primaryStage.setScene(loginScene);
        primaryStage.show();


        loginScreen.setOnLoginSuccess(() -> {
            Scene profileCreationScene = new Scene(profileCreationScreen.getPane(), 800, 600);
            primaryStage.setScene(profileCreationScene);
        });


    }
}