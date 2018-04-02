package ru.sberbank.homework.chat.core;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Connection implements Closeable, AutoCloseable {
    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    private final List<Message> messages = new CopyOnWriteArrayList<>();

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    public void send(Message message) throws IOException {
        synchronized(out) {
            out.writeObject(message);
        }
    }

    public void sendAll() throws IOException {
        for (Message message : messages) {
            send(message);
        }
    }

    public Message receive() throws IOException, ClassNotFoundException {
        synchronized(in) {
            return (Message) in.readObject();
        }
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void close() throws IOException {
        socket.close();
        out.close();
        in.close();
    }
}
