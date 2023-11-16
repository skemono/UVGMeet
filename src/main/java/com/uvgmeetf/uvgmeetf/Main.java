package com.uvgmeetf.uvgmeetf;


import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static UVGMeetDB meetDB;
    private static SceneManager sceneManager;
    private static Session sessionManager;

    private static UVGMeetStorage storageManager;

    @Override
    public void start(Stage stage) throws IOException {
        String currentWorkingDirectory = System.getProperty("user.dir");

        System.out.println("The current working directory is: " + currentWorkingDirectory);
        sceneManager = new SceneManager(stage);
        meetDB = new UVGMeetDB();
        sceneManager.setFXML("registrar.fxml");
        sessionManager = new Session();
        storageManager = new UVGMeetStorage();

    }

    public static UVGMeetDB getMeetDB(){
        return meetDB;
    }

    public static SceneManager getSceneManager(){
        return sceneManager;
    }

    public static Session getSessionManager() {
        return sessionManager;
    }

    public static UVGMeetStorage getStorageManager() {
        return storageManager;
    }

    public static void main(String[] args) {
        launch();
    }
}