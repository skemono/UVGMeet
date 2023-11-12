package com.uvgmeetf.uvgmeetf;


import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static UVGMeetDB meetDB;
    private static SceneManager sceneManager;

    @Override
    public void start(Stage stage) throws IOException {
        sceneManager = new SceneManager(stage);
        meetDB = new UVGMeetDB();
        sceneManager.setFXML("registrar.fxml");

    }

    public static UVGMeetDB getMeetDB(){
        return meetDB;
    }

    public static SceneManager getSceneManager(){
        return sceneManager;
    }

    public static void main(String[] args) {
        launch();
    }
}