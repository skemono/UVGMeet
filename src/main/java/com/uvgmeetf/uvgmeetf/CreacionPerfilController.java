package com.uvgmeetf.uvgmeetf;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import java.util.HashMap;
import java.util.Map;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

public class CreacionPerfilController {

    @FXML
    private TextArea bio;

    @FXML
    private TextField carrera;

    @FXML
    private Button crearPerfilBton;

    @FXML
    private AnchorPane bottomPane;

    @FXML
    private TextField edad;

    @FXML
    private TextArea gusto;

    @FXML
    private Label registrarError;

    @FXML
    private Button subirImageBton;

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


    private boolean imageUploaded = false;

    void showError(String txt){
        this.registrarError.setStyle("-fx-text-fill: red");
        this.registrarError.setText(txt);
        this.registrarError.requestLayout();

    }

    void showMessage(String txt){
        this.registrarError.setStyle("-fx-text-fill: black");
        this.registrarError.setText(txt);
        this.registrarError.requestLayout();
    }

    public CreacionPerfilController() throws ExecutionException, InterruptedException {
        Map<String, Object> pollo = baseDatos.leer("usuario", baseDatos.verificarExistencia("usuario", "id", sesion.getId()));
        if (pollo.get("bio") != null){
            Platform.runLater(() -> {
                bio.setText(pollo.get("bio").toString());
                carrera.setText(pollo.get("carrera").toString());
                edad.setText(pollo.get("edad").toString());
                gusto.setText(pollo.get("gustos").toString());
                imageUploaded = true;
                bottomPane.setVisible(true);
            });
        }
    }
    @FXML
    void crearPerfil(ActionEvent event) throws ExecutionException, InterruptedException, IOException {
        String biografia = this.bio.getText();
        String career = this.carrera.getText();
        String age = this.edad.getText();
        String likes = this.gusto.getText();

        if (biografia.isEmpty() || career.isEmpty() || age.isEmpty() || likes.isEmpty() || !this.imageUploaded){
            showError("Debe llenar todos los campos.");
            return;
        }

        Map<String, Object> usuarioCompleto = new HashMap<>();
        usuarioCompleto.put("bio", biografia);
        usuarioCompleto.put("carrera", career);
        usuarioCompleto.put("edad", age);
        usuarioCompleto.put("gustos", likes);


        this.baseDatos.actualizar("usuario", this.baseDatos.verificarExistencia("usuario", "id", this.sesion.getId()), usuarioCompleto);
        UVGMeetStorage.uploadObject("uvgmeetprofilepics",this.sesion.getId()+".png", "src\\main\\resources\\com\\uvgmeetf\\uvgmeetf\\SessionAssets\\"+this.sesion.getId()+".png");
        showMessage("Perfil creado exitosamente.");
        this.sceneManager.setFXML("matching.fxml");


    }

    @FXML
    void subirImagen(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Path path = Paths.get("src\\main\\resources\\com\\uvgmeetf\\uvgmeetf\\SessionAssets\\"+this.sesion.getId()+".png");

            // Create a Files object to copy the image file to the path.
            Files.copy(selectedFile.toPath(), path);
            this.imageUploaded = true;
        }
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
