package ru.sberbank.homework.chat.core;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final Connection connection;
    private final List messages = new ArrayList();

    public User(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public List getMessages() {
        return messages;
    }
}
