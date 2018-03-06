package ru.sberbank.homework.kalugin;

class ErrorMessage {
    private String message;
    private boolean hasMessage;

    ErrorMessage() {}
    ErrorMessage(String s) {
        message = s;
        hasMessage = true;
    }

    public boolean hasMessage() {
        return hasMessage;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String s) {
        message = s;
        hasMessage = true;
    }
}
