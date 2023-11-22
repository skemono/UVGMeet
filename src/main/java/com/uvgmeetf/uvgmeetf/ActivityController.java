package com.uvgmeetf.uvgmeetf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ActivityController {

    @FXML
    private Button activityScreenBton;

    @FXML
    private AnchorPane bottomPane;

    @FXML
    private Button chatScreenBton;

    @FXML
    private Button matchScreenBton;

    @FXML
    private Button profileScreenBton;

    private SceneManager sceneManager = Main.getSceneManager();

    @FXML
    void activadeMatchS(ActionEvent event) throws IOException {
        sceneManager.setFXML("matching.fxml");
    }

    @FXML
    void activateActivityS(ActionEvent event) throws IOException {
        sceneManager.setFXML("actividad.fxml");
    }

    @FXML
    void activateChatS(ActionEvent event) throws IOException {
        sceneManager.setFXML("chat.fxml");
    }

    @FXML
    void activateProfileS(ActionEvent event) throws IOException {
        sceneManager.setFXML("perfil.fxml");
    }

}
