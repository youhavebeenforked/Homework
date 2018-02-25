package ru.sberbank.homework.Polushin;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class SimpleCalculator implements Runnable {
    private Scanner scanner;
    private OutputStreamWriter streamWriter;
    private Calculation calculator = new Calculation();

    public SimpleCalculator(Scanner sc, OutputStreamWriter streamWriter) {
        this.scanner = sc;
        this.streamWriter = streamWriter;
    }

    @Override
    public void run() {
        try {
            streamWriter.write("Hello! This is Simple Calculator v. 0.1a! \n" +
                    "Input your expression like a a_@_b or @_b; " +
                    "for exit program input 'quit'.\n");
            streamWriter.flush();
            do {
                String userInput = scanner.nextLine();
                String output = calculator.calculate(userInput);
                streamWriter.write(output);
                streamWriter.flush();

            } while (scanner.hasNext());
        } catch (IOException e) {
            System.out.println("Can't get outstream");
            e.printStackTrace();
        }
    }
}