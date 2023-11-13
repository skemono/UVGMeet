package com.uvgmeetf.uvgmeetf;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class MatchingController {

    @FXML
    private Button activityBton;

    @FXML
    private Button btonDislike;

    @FXML
    private Button btonLike;

    @FXML
    private Label carrera;

    @FXML
    private Button chatBton;

    @FXML
    private ImageView likedView;

    @FXML
    private Button matchBton;

    @FXML
    private ImageView pfp;

    @FXML
    private Button profileBton;

    @FXML
    private Label titulo;

    @FXML
    private Rectangle transitionRectangle;

    private UVGMeetDB baseDatos = Main.getMeetDB();
    private Session sesion = Main.getSessionManager();
    private List<Map<String, Object>> displayUsers = new ArrayList<>();

    private Map<String, Object> interestedDoc = new HashMap<>();
    private Image bigDislike = new Image(Main.class.getResourceAsStream("bigdislike.png"));
    private Image bigLike = new Image(Main.class.getResourceAsStream("biglike.png"));

    void setOpacity(int value){
        FadeTransition transition = new FadeTransition();
        transition.setNode(this.transitionRectangle);
        transition.setFromValue(this.transitionRectangle.getOpacity());
        transition.setToValue(value);
        transition.setDuration(Duration.seconds(1));
        transition.play();
    }

    private int currentIndex = 0;
    public void changeUser() {
        setOpacity(1);
        Platform.runLater(() -> {
            Map<String, Object> currentUser = displayUsers.get(currentIndex);
            titulo.setText(currentUser.get("nombre").toString()+", "+currentUser.get("edad").toString());
            carrera.setText("Estudia "+currentUser.get("carrera").toString());
            UVGMeetStorage.downloadObject("uvgmeetprofilepics", currentUser.get("id")+".png", "src\\main\\resources\\com\\uvgmeetf\\uvgmeetf\\SessionAssets\\"+currentUser.get("id")+".png");
            Platform.runLater(() -> {
                Image userpfp = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("src\\main\\resources\\com\\uvgmeetf\\uvgmeetf\\SessionAssets\\" + currentUser.get("id") + ".png")));
                pfp.setImage(userpfp);
                setOpacity(0);
                likedView.setOpacity(0);
                currentIndex += 1;
            });
        });

    }
    public MatchingController() throws ExecutionException, InterruptedException, FileNotFoundException {
        interestedDoc.put("a","a");
        List<Map<String, Object>> opa = baseDatos.leer("usuario");
        for (Map<String, Object> map : opa) {
            if (baseDatos.verficarExistenciaDoc("usuario", baseDatos.verificarExistencia("usuario", "id", sesion.getId()), "visto", map.get("id").toString()).isEmpty() && !map.get("id").equals(sesion.getId())){
                displayUsers.add(map);
            }
        }
        Platform.runLater(this::changeUser);
    }

    @FXML
    void meDisgusta(ActionEvent event) throws ExecutionException, InterruptedException {
        likedView.setImage(bigDislike);
        likedView.setOpacity(1);
        baseDatos.agregar("usuario", baseDatos.verificarExistencia("usuario", "id", sesion.getId()), "visto", displayUsers.get(currentIndex).get("id").toString(), interestedDoc);
        changeUser();
    }

    @FXML
    void meGusta(ActionEvent event) throws ExecutionException, InterruptedException {
        likedView.setImage(bigLike);
        likedView.setOpacity(1);
        baseDatos.agregar("usuario",
                baseDatos.verificarExistencia("usuario", "id", displayUsers.get(currentIndex).get("id")),
                "interesado",
                String.valueOf(sesion.getId()),
                interestedDoc);
        baseDatos.agregar("usuario", baseDatos.verificarExistencia("usuario", "id", sesion.getId()), "visto", displayUsers.get(currentIndex).get("id").toString(), interestedDoc);
        changeUser();
    }

    @FXML
    void setActivity(ActionEvent event) {

    }

    @FXML
    void setChat(ActionEvent event) {

    }

    @FXML
    void setMatch(ActionEvent event) {

    }

    @FXML
    void setProfile(ActionEvent event) {

    }

}
