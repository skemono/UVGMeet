package com.uvgmeetf.uvgmeetf;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneManager {
    private Stage stage;

    public SceneManager(Stage gotStage){
        this.stage = gotStage;
    }
    public void setFXML(String fxmlPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlPath));
        Scene scene = new Scene(fxmlLoader.load());
        this.stage.setTitle("UVGMeet");
        this.stage.setScene(scene);
        this.stage.show();
    }
}
