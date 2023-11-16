package com.uvgmeetf.uvgmeetf;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ProfileController {

    @FXML
    private TextArea bio;

    @FXML
    private Label carrera;

    @FXML
    private Label edad;

    @FXML
    private Hyperlink editSender;

    @FXML
    private TextArea gustos;

    @FXML
    private Label registrarError;

    @FXML
    private ImageView userpfp;

    @FXML
    private Button activityScreenBton;

    @FXML
    private Button chatScreenBton;
    @FXML
    private Button matchScreenBton;

    @FXML
    private Button profileScreenBton;

    @FXML
    private Rectangle transitionRectangle;

    private UVGMeetDB baseDatos = Main.getMeetDB();
    private Session sesion = Main.getSessionManager();

    private SceneManager sceneManager = Main.getSceneManager();


    private void disableRectangle(ActionEvent actionEven){
        transitionRectangle.setVisible(false);
    }
    void setOpacity(int value){
        FadeTransition transition = new FadeTransition();
        transition.setNode(this.transitionRectangle);
        transition.setFromValue(this.transitionRectangle.getOpacity());
        transition.setToValue(value);
        transition.setDuration(Duration.seconds(1));
        transition.play();
        transition.setOnFinished(this::disableRectangle);
    }


    @FXML
    void sendToEdit(ActionEvent event) throws ExecutionException, InterruptedException, IOException {
        sceneManager.setFXML("creacionPerfil.fxml");
    }

    public ProfileController() throws ExecutionException, InterruptedException {
        Map<String, Object> pollo = baseDatos.leer("usuario", baseDatos.verificarExistencia("usuario", "id", sesion.getId()));
        Platform.runLater(() -> {
            bio.setText(pollo.get("bio").toString());
            carrera.setText(pollo.get("carrera").toString());
            edad.setText(pollo.get("edad").toString());
            gustos.setText(pollo.get("gustos").toString());
            UVGMeetStorage.downloadObject("uvgmeetprofilepics", sesion.getId()+".png", "src\\main\\resources\\com\\uvgmeetf\\uvgmeetf\\SessionAssets\\"+sesion.getId()+".png");
            Platform.runLater(() -> {
                Image pfp = null;

                while (true) {
                    File file = new File("src\\main\\resources\\com\\uvgmeetf\\uvgmeetf\\SessionAssets\\" + sesion.getId() + ".png");
                    if (file.exists()) {
                        pfp = new Image(file.toURI().toString());
                        break;
                    }
                }

                //Image userpfp = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("src\\main\\resources\\com\\uvgmeetf\\uvgmeetf\\SessionAssets\\" + currentUser.get("id") + ".png")));
                userpfp.setImage(pfp);
                setOpacity(0);

            });
            bio.setDisable(true);
            carrera.setDisable(true);
            edad.setDisable(true);
            gustos.setDisable(true);
        });
    }

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
