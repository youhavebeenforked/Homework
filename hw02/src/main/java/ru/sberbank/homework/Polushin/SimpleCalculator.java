package ru.sberbank.homework.Polushin;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class SimpleCalculator implements Runnable {
    private Scanner scanner;
    private OutputStreamWriter streamWriter;

    public SimpleCalculator(Scanner sc, OutputStreamWriter streamWriter) {
        this.scanner = sc;
        this.streamWriter = streamWriter;
    }

    @Override
    public void run() {
        try {
            streamWriter.write("Hello! This is Simple Calculater v. 0.1a!");
        } catch (IOException e) {
            System.out.println("Can't get outstream");
            e.printStackTrace();
        }
    }
}