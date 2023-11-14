package com.uvgmeetf.uvgmeetf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class LoginController {

    @FXML
    private TextField email;

    @FXML
    private Button logBton;

    @FXML
    private PasswordField passwd;

    @FXML
    private Label registrarError;

    void showError(String txt){
        this.registrarError.setStyle("-fx-text-fill: red");
        this.registrarError.setText(txt);
        this.registrarError.requestLayout();

    }

    private UVGMeetDB baseDatos = Main.getMeetDB();
    private Session sesion = Main.getSessionManager();

    private SceneManager sceneManager = Main.getSceneManager();
    private String sessionStarted = "";
    @FXML
    void login(ActionEvent event) throws ExecutionException, InterruptedException, IOException {
        String correo = email.getText();
        String pass = passwd.getText();

        List<Map<String, Object>> readForLogin = baseDatos.leer("usuario");
        for (Map<String, Object> map : readForLogin) {
            if (map.get("correo").toString().equals(correo) && map.get("passwd").toString().equals(pass)) {
                sesion.setUser(map.get("nombre").toString());
                sesion.setId(Integer.parseInt(map.get("id").toString()));
                if (map.get("bio") != null){
                    sessionStarted = "profileCreated";
                } else{
                    sessionStarted = "profilePending";
                }


            }else {
                showError("Correo o contraseña incorrectos.");
            }
        }

        if (sessionStarted.equals("profileCreated")){
            sceneManager.setFXML("matching.fxml");
        } else if(sessionStarted.equals("profilePending")){
            sceneManager.setFXML("creacionPerfil.fxml");
        }
    }



}
