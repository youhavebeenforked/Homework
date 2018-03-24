package ru.sberbank.homework.chat.client;

import ru.sberbank.homework.chat.core.Connection;
import ru.sberbank.homework.chat.core.Message;
import ru.sberbank.homework.chat.core.MessageType;
import ru.sberbank.homework.chat.writer.ConsoleWriter;
import ru.sberbank.homework.chat.writer.Writer;

import java.io.IOException;
import java.net.Socket;

import static ru.sberbank.homework.chat.util.ConsoleHelper.readString;


public class Client {
    private final String serverAddress;
    private final int serverPort;
    private String userName;
    private Connection connection;
    private volatile boolean clientConnected = false;
    private Writer writer = new ConsoleWriter();

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void startClient() {
        Thread socketThread = new Thread(this::runThreadCheckMessages);
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                writer.write("Ошибка " + e.getMessage());
                return;
            }
        }
        if (clientConnected) {
            writer.write("Соединение установлено. Для выхода наберите команду ‘exit’.");
        } else {
            writer.write("Произошла ошибка во время работы программы");
        }
        while (clientConnected) {
            String text = readString();
            if (text.equals("exit")) break;
            sendTextMessage(text);
        }
    }

    private void runThreadCheckMessages() {
        try (Socket socket = new Socket(serverAddress, serverPort);
             Connection connection = new Connection(socket)) {
            this.connection = connection;
            connection();
            continuousCheckingOfMessages();
        } catch (IOException | ClassNotFoundException e) {
            writer.write("Ошибка " + e.getMessage());
            notifyConnectionStatusChanged(false);
        }
    }

    private void printIncomingMessage(Message message) {
        writer.write(message.getFromUser() + " >> " + message.getData());
    }

    private void notifyConnectionStatusChanged(boolean clientConnected) {
        synchronized (Client.this) {
            Client.this.clientConnected = clientConnected;
            Client.this.notify();
        }
    }

    private void connection() throws IOException, ClassNotFoundException {
        while (true) {
            Message message = connection.receive();
            if (message.getType() == MessageType.NAME_REQUEST) {
                userName = getUserName();
                connection.send(new Message(MessageType.USER_NAME, userName, userName));
            } else if (message.getType() == MessageType.NAME_ACCEPTED) {
                notifyConnectionStatusChanged(true);
                break;
            } else {
                throw new IOException("Unexpected MessageType");
            }
        }
    }

    private void continuousCheckingOfMessages() throws IOException, ClassNotFoundException {
        while (true) {
            Message message = connection.receive();
            if (message.getType() == MessageType.TEXT) {
                printIncomingMessage(message);
            } else {
                throw new IOException("Unexpected MessageType");
            }
        }
    }

    private String getUserName() {
        writer.write("Введите ваш логин. Логин должен быть уникальным");
        return readString();
    }

    private void sendTextMessage(String text) {
        try {
            Message message;
            if (text.equals("получить")) {
                message = new Message(MessageType.GET_MESSAGES);
            } else {
                String destination = text.split(">>")[0].trim();
                String textMessage = text.substring(destination.length() + 2);
                message = new Message(MessageType.TEXT, textMessage, userName, destination);
            }
            connection.send(message);
        } catch (IOException e) {
            writer.write("Сообщение не было отправлено");
            clientConnected = false;
        }
    }
}
