package ru.sberbank.homework.karaush.UrlReader;

class IncorrectUrlFormatException extends RuntimeException{
    IncorrectUrlFormatException(String s){
        super(s);
    }
}
