package com.uvgmeetf.uvgmeetf;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ChatController {

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

    @FXML
    private Button userBton;

    @FXML
    private TextArea chatorea;

    @FXML
    private ImageView userImage;

    @FXML
    private Label userName;

    @FXML
    private GridPane userPane;

    @FXML
    private VBox tataVbox;

    @FXML
    private TextField msg;



    private UVGMeetDB baseDatos = Main.getMeetDB();
    private Session sesion = Main.getSessionManager();

    private SceneManager sceneManager = Main.getSceneManager();

    private String chattingWith = "";

    private String nameLocal = "";
    private String nameWho = "";

    private String docNameFinal = "";

    @FXML
    void sendMessage(ActionEvent event) throws ExecutionException, InterruptedException {
        System.out.println("Beginning");
        System.out.println(msg.getText());
        if (!msg.getText().isEmpty()){
            Object currentChat = baseDatos.leer("chat", docNameFinal).get("chat");
            if (currentChat != null){
                String actualChat = currentChat.toString();
                actualChat = actualChat+nameLocal+"|||"+msg.getText()+"```/`";
                Map<String, Object> newChat = new HashMap<>();
                newChat.put("chat", actualChat);

                baseDatos.actualizar("chat", docNameFinal, newChat);
            } else{
                String actualChat = "";
                actualChat = actualChat+nameLocal+"|||"+msg.getText()+"```/`";
                Map<String, Object> newChat = new HashMap<>();
                newChat.put("chat", actualChat);

                baseDatos.actualizar("chat", docNameFinal, newChat);
            }
        }
        msg.setText("");
        loadChat(chattingWith);
    }

    void loadChat(String withWho) throws ExecutionException, InterruptedException {
        chattingWith = withWho;
        System.out.println(chattingWith);


        List<Map<String, Object>> todosChats =  baseDatos.leer("chat");
        for (Map<String, Object> chat : todosChats){
            boolean t1 = chat.get("p1").toString().equals(chattingWith) && chat.get("p2").toString().equals(String.valueOf(sesion.getId()));
            boolean t2 = chat.get("p2").toString().equals(chattingWith) && chat.get("p1").toString().equals(String.valueOf(sesion.getId()));

            String docname = baseDatos.verificarExistenciaDos("chat", "p1", Integer.parseInt(chattingWith), "p2", sesion.getId());
            String docname2 = baseDatos.verificarExistenciaDos("chat", "p2", Integer.parseInt(chattingWith), "p1", sesion.getId());

            if(docname.isEmpty()){docNameFinal = docname2;}else{docNameFinal = docname;}
            if (t1 || t2){
                nameLocal = baseDatos.leer("usuario", baseDatos.verificarExistencia("usuario", "id", sesion.getId())).get("nombre").toString();
                nameWho = baseDatos.leer("usuario", baseDatos.verificarExistencia("usuario", "id", Integer.parseInt(chattingWith))).get("nombre").toString();

                if (chat.get("chat") != null){
                    Map<String, String> idToNameMap = new HashMap<>();
                    idToNameMap.put(String.valueOf(sesion.getId()) , nameLocal);
                    idToNameMap.put(chattingWith , nameWho);

                    String input = chat.get("chat").toString();

                    // Map to store ID-to-Name mapping

                    // Parse the combined string and output the results

                    chatorea.setDisable(false);
                    chatorea.setText(input.replace("|||", ": ").replace("```/`", "\n"));
                    chatorea.setDisable(true);

                }
            }
        }

    }


    public ChatController() throws ExecutionException, InterruptedException, IOException, ClassNotFoundException {
        List<Map<String, Object>> interesados = baseDatos.leer("interes");
        List<Map<String, Object>> usuarios = baseDatos.leer("usuario");
        List<String> meInteresa = new ArrayList<>();
        List<String> leIntereso = new ArrayList<>();
        for (Map<String, Object> user : interesados){
            for (Map.Entry<String, Object> interes : user.entrySet()){
                System.out.println("Comparando 1 " + interes.getKey() + " y " + sesion.getId());
                System.out.println("Comparando 2 " + interes.getValue() + " y " + sesion.getId());
                if (interes.getKey().equals(String.valueOf(sesion.getId()))){
                     meInteresa.add(interes.getValue().toString());
                     System.out.println("Me interesa " + meInteresa);

                } else if(interes.getValue().toString().equals(String.valueOf(sesion.getId()))){
                    leIntereso.add(interes.getKey());
                    System.out.println("Le intereso a " + leIntereso);
                }
            }
        }

        for (String ina : meInteresa){
            for (String ona : leIntereso){
                System.out.println("Comparando " + ina + " y " + ona);
                if (ina.equals(ona)){
                    Map<String, Object> chatCreation = new HashMap<>();
                    chatCreation.put("p1", sesion.getId());
                    chatCreation.put("p2", Integer.valueOf(ona));
                    baseDatos.agregar("chat", "chat"+baseDatos.nextNumberOfDoc("chat"), chatCreation);
                    baseDatos.eliminar("interes", baseDatos.verificarExistenciaDoc("interes", ina+":"+sesion.getId()));
                    baseDatos.eliminar("interes", baseDatos.verificarExistenciaDoc("interes", sesion.getId()+":"+ina));
                }
            }
        }

        List<Map<String, Object>> chats = baseDatos.leer("chat");

        for (Map<String, Object> chat : chats){
            boolean test1 = chat.get("p1").toString().equals(String.valueOf(sesion.getId()));
            boolean test2 = chat.get("p2").toString().equals(String.valueOf(sesion.getId()));
            String whichUserToUse;
            if (test1 || test2){
                if (test1){whichUserToUse = chat.get("p2").toString();} else {whichUserToUse = chat.get("p1").toString();}
                String name = usuarios.stream().filter(user -> user.get("id").toString().equals(whichUserToUse)).findFirst().map(user -> user.get("nombre").toString()).orElse("");

                System.out.println("high");
                Label newUserName = new Label();
                ImageView newUserImage = new ImageView();
                Button newUserButton = new Button();
                GridPane newUserPane = new GridPane();


                newUserButton.setText("Abrir");
                newUserPane.prefHeight(110);
                newUserPane.prefWidth(78);

                newUserImage.setFitHeight(68);
                newUserImage.setFitWidth(65);

                newUserName.setPrefHeight(18);
                newUserName.setPrefWidth(100);

                newUserButton.setPrefHeight(26);
                newUserButton.setPrefWidth(60);

                newUserPane.add(newUserImage, 0, 1, 1, 1);
                newUserPane.add(newUserName, 0, 0, 1, 1);
                newUserPane.add(newUserButton, 0, 2, 1, 1);


                newUserButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            loadChat(whichUserToUse); // Call the function and handle its return value if needed
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

                newUserName.setText(name);
                UVGMeetStorage.downloadObject("uvgmeetprofilepics", whichUserToUse+".png", "src\\main\\resources\\com\\uvgmeetf\\uvgmeetf\\SessionAssets\\"+whichUserToUse+".png");
                Platform.runLater(() -> {
                    Image userpfp = null;

                    while (true) {
                        File file = new File("src\\main\\resources\\com\\uvgmeetf\\uvgmeetf\\SessionAssets\\" + whichUserToUse + ".png");
                        if (file.exists()) {
                            userpfp = new Image(file.toURI().toString());
                            break;
                        }

                    }
                    newUserImage.setImage(userpfp);
                    tataVbox.getChildren().add(newUserPane);
                });


            } else {
                whichUserToUse = "";
            }
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
