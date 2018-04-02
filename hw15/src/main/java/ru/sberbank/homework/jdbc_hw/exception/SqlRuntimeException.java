package ru.sberbank.homework.jdbc_hw.exception;

public class SqlRuntimeException extends RuntimeException {
    public SqlRuntimeException(Throwable cause) {
        super(cause);
    }
}
