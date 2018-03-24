package ru.sberbank.homework.andreev.task_03.cache;

public class CacheProxyException extends RuntimeException {
    public CacheProxyException(String message) {
        super(message);
    }

    public CacheProxyException(String message, Throwable cause) {
        super(message, cause);
    }
}
