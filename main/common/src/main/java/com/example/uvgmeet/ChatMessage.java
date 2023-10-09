package com.example.uvgmeet;

import java.util.Date;

public class ChatMessage {
    private String content;
    private Date timestamp;
    private String senderUsername;
    private boolean isMine;

    // Constructor
    public ChatMessage(String content, Date timestamp, String senderUsername, boolean isMine) {
        this.content = content;
        this.timestamp = timestamp;
        this.senderUsername = senderUsername;
        this.isMine = isMine;
    }

    // Métodos getter y setter para los atributos del mensaje
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", senderUsername='" + senderUsername + '\'' +
                ", isMine=" + isMine +
                '}';
    }
}
