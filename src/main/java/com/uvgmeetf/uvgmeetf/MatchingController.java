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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class MatchingController {

    @FXML
    private Button btonDislike;

    @FXML
    private Button btonLike;

    @FXML
    private Label carrera;

    @FXML
    private ImageView likedView;

    @FXML
    private ImageView pfp;

    @FXML
    private Label noUsersLeft1;

    @FXML
    private Label noUsersLeft2;


    @FXML
    private Label titulo;

    @FXML
    private Rectangle transitionRectangle;

    @FXML
    private Button activityScreenBton;

    @FXML
    private Button chatScreenBton;
    @FXML
    private Button matchScreenBton;

    @FXML
    private Button profileScreenBton;

    private UVGMeetDB baseDatos = Main.getMeetDB();
    private Session sesion = Main.getSessionManager();

    private SceneManager sceneManager = Main.getSceneManager();
    private List<Map<String, Object>> displayUsers = new ArrayList<>();
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

    void ranOutUsers(){
        setOpacity(1);
        btonLike.setDisable(true);
        btonDislike.setDisable(true);

        btonDislike.setVisible(false);
        btonLike.setVisible(false);

        noUsersLeft1.setVisible(true);
        noUsersLeft2.setVisible(true);
    }
    public void changeUser() {
        if (currentIndex >= displayUsers.size()){
            System.out.println("bigger");
            ranOutUsers();
        }
        setOpacity(1);
        Platform.runLater(() -> {
            if (currentIndex >= displayUsers.size()){
                return;
            }
            Map<String, Object> currentUser = displayUsers.get(currentIndex);
            titulo.setText(currentUser.get("nombre").toString()+", "+currentUser.get("edad").toString());
            carrera.setText("Estudia "+currentUser.get("carrera").toString());
            UVGMeetStorage.downloadObject("uvgmeetprofilepics", currentUser.get("id")+".png", "src\\main\\resources\\com\\uvgmeetf\\uvgmeetf\\SessionAssets\\"+currentUser.get("id")+".png");
            Platform.runLater(() -> {
                Image userpfp = null;

                while (true) {
                    File file = new File("src\\main\\resources\\com\\uvgmeetf\\uvgmeetf\\SessionAssets\\" + currentUser.get("id") + ".png");
                    if (file.exists()) {
                        userpfp = new Image(file.toURI().toString());
                        break;
                    }

                }

                //Image userpfp = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("src\\main\\resources\\com\\uvgmeetf\\uvgmeetf\\SessionAssets\\" + currentUser.get("id") + ".png")));
                pfp.setImage(userpfp);
                setOpacity(0);
                likedView.setOpacity(0);
            });
        });
    }
    public MatchingController() throws ExecutionException, InterruptedException, FileNotFoundException {

        List<Map<String, Object>> opa = baseDatos.leer("usuario");
        List<String> popa = baseDatos.leerNombreDoc("usuario", baseDatos.verificarExistencia("usuario", "id", String.valueOf(sesion.getId())), "visto");
        for (Map<String, Object> map : opa) {
            if (map.get("id") != null){
                boolean equalswot = map.get("id").toString().equals(String.valueOf(sesion.getId()));
                boolean viewed = false;

                for (String docID : popa){
                    if (docID.equals(map.get("id").toString())){
                        System.out.println("Comparando " + docID + " con " + map.get("id"));
                        viewed = true;
                    }
                }
                if (!viewed && !equalswot && map.get("bio") != null){
                    displayUsers.add(map);
                    System.out.println(displayUsers.toString());
                }
            }
        }
        if (!displayUsers.isEmpty()){
            Platform.runLater(this::changeUser);
        }else {
            Platform.runLater(this::ranOutUsers);
        }
    }

    @FXML
    void meDisgusta(ActionEvent event) throws ExecutionException, InterruptedException {
        Map<String, Object> interestedDoc = new HashMap<>();
        interestedDoc.put("a","a");
        likedView.setImage(bigDislike);
        likedView.setOpacity(1);
        baseDatos.agregar("usuario", baseDatos.verificarExistencia("usuario", "id", sesion.getId()), "visto", displayUsers.get(currentIndex).get("id").toString(), interestedDoc);
        currentIndex += 1;
        changeUser();
    }

    @FXML
    void meGusta(ActionEvent event) throws ExecutionException, InterruptedException {
        Map<String, Object> interestedDoc = new HashMap<>();
        likedView.setImage(bigLike);
        likedView.setOpacity(1);
        interestedDoc.put(String.valueOf(sesion.getId()), displayUsers.get(currentIndex).get("id"));

        baseDatos.agregar("interes", sesion.getId() + ":" + displayUsers.get(currentIndex).get("id"), interestedDoc);
        baseDatos.agregar("usuario", baseDatos.verificarExistencia("usuario", "id", sesion.getId()), "visto", displayUsers.get(currentIndex).get("id").toString(), interestedDoc);
        currentIndex += 1;
        changeUser();
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
