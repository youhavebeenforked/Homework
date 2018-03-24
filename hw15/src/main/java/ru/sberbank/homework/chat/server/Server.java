package ru.sberbank.homework.chat.server;

import ru.sberbank.homework.chat.core.Connection;
import ru.sberbank.homework.chat.core.Message;
import ru.sberbank.homework.chat.core.MessageType;
import ru.sberbank.homework.chat.writer.ConsoleWriter;
import ru.sberbank.homework.chat.writer.Writer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private final int serverPort;
    private final Map<String, Connection> connectionMap = new ConcurrentHashMap<>();
    private final Writer writer = new ConsoleWriter();

    public Server(final int serverPort) {
        this.serverPort = serverPort;
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            writer.write("Сервер запущен на порту " + serverPort);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> runThreadNewUser(socket)).start();
            }
        } catch (IOException e) {
            writer.write(e.getMessage());
        }
    }

    private void runThreadNewUser(Socket socket) {
        try (Connection connection = new Connection(socket)) {
            String userName = connection(connection);
            sendMessage(new Message(MessageType.TEXT, userName + " присоединился к чату", "system"), userName);
            sendListOfUsers(connection, userName);
            continuousCheckingOfMessages(connection, userName);
            if (userName != null) {
                connectionMap.remove(userName);
                sendMessage(new Message(MessageType.TEXT, userName + " вышел из чата", "system"), userName);
            }
        } catch (ClassNotFoundException | IOException e) {
            writer.write(e.getMessage());
        }
    }

    private String connection(Connection connection) throws IOException, ClassNotFoundException {
        boolean connected = false;
        Message messageUserName = null;
        while (!connected) {
            Message messageNameRequest = new Message(MessageType.NAME_REQUEST);
            connection.send(messageNameRequest);
            messageUserName = connection.receive();
            if (messageUserName.getType() != MessageType.USER_NAME) continue;
            if (messageUserName.getType() == MessageType.USER_NAME &&
                    !messageUserName.getData().isEmpty()) {
                if (!connectionMap.containsKey(messageUserName.getData())) {
                    connectionMap.put(messageUserName.getData(), connection);
                    connection.send(new Message(MessageType.NAME_ACCEPTED));
                    writer.write(messageUserName.getData() + " принят");
                    connected = true;
                }
            }
        }
        return messageUserName.getData();
    }

    private void sendMessage(Message message, String userName) {
        for (Map.Entry entry : connectionMap.entrySet()) {
            String name = entry.getKey().toString();
            if (!name.equals(userName)) {
                Connection connection = (Connection) entry.getValue();
                try {
                    connection.send(message);
                } catch (IOException e) {
                    writer.write(e.getMessage());
                }
            }
        }
    }

    private void addMessage(Message message, String userName) {
        for (Map.Entry entry : connectionMap.entrySet()) {
            String name = entry.getKey().toString();
            if (!name.equals(userName) && message.getDestination().equals(name)) {
                Connection connection = (Connection) entry.getValue();
                connection.addMessage(message);
            }
        }
    }

    private void sendListOfUsers(Connection connection, String userName) throws IOException {
        for (Map.Entry entry : connectionMap.entrySet()) {
            String name = entry.getKey().toString();
            if (!name.equals(userName)) {
                connection.send(new Message(MessageType.TEXT, name + " онлайн", "system"));
            }
        }
    }

    private void continuousCheckingOfMessages(Connection connection, String userName) throws IOException, ClassNotFoundException {
        while (true) {
            Message message = connection.receive();
            if (message.getType() == MessageType.TEXT) {
//                String text = userName + ": " + message.getData();
//                Message newMessage = new Message(MessageType.TEXT, text);
                addMessage(message, userName);
            } else if (message.getType() == MessageType.GET_MESSAGES) {
                connection.sendAll();
            } else {
                writer.write("Ошибка типа сообщения от пользователя " + userName);
            }
        }
    }
}

