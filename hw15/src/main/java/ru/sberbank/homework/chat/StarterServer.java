package ru.sberbank.homework.chat;

import ru.sberbank.homework.chat.server.Server;

public class StarterServer {
    public static void main(String[] args) {
        Server server = new Server(9999);
        server.startServer();
    }
}
