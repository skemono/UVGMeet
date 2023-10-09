package com.example.uvgmeet;

public class Chat {
    private String sender; // Nombre del remitente del mensaje
    private String message; // Contenido del mensaje

    // Constructor
    public Chat(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    // Getters y setters (puedes agregar más según tus necesidades)
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
