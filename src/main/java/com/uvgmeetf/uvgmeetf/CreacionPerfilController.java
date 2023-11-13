package com.uvgmeetf.uvgmeetf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private TextField edad;

    @FXML
    private TextArea gusto;

    @FXML
    private Label registrarError;

    @FXML
    private Button subirImageBton;

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
        // Create a FileChooser object.
        FileChooser fileChooser = new FileChooser();

        // Set the FileChooser to select image files.
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png"));

        // Show the FileChooser dialog.
        File selectedFile = fileChooser.showOpenDialog(null);

        // Get the selected file from the FileChooser.
        if (selectedFile != null) {
            // Create a Path object to the path where you want to save the image.
            Path path = Paths.get("src\\main\\resources\\com\\uvgmeetf\\uvgmeetf\\SessionAssets\\"+this.sesion.getId()+".png");

            // Create a Files object to copy the image file to the path.
            Files.copy(selectedFile.toPath(), path);
            this.imageUploaded = true;
        }
    }

}
