package ru.sberbank.homework.chat.core;


import java.io.Serializable;

public class Message implements Serializable {
    private final MessageType type;
    private final String data;
    private final String fromUser;
    private String destination;

    public Message(MessageType type) {
        this.type = type;
        this.data = null;
        fromUser = null;
    }

    public Message(MessageType type, String data, String fromUser) {
        this.type = type;
        this.data = data;
        this.fromUser = fromUser;
    }

    public Message(MessageType type, String data, String fromUser, String destination) {
        this.type = type;
        this.data = data;
        this.fromUser = fromUser;
        this.destination = destination;
    }

    public MessageType getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    public String getFromUser() {
        return fromUser;
    }

    public String getDestination() {

        return destination;
    }
}
