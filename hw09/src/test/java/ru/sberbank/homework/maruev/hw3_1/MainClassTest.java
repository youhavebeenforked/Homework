package ru.sberbank.homework.maruev.hw3_1;

import org.junit.Test;
import ru.sberbank.homework.maruev.hw3_1.exceptions.ExitException;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * Created by Иван.
 */
public class MainClassTest {
    private ByteArrayInputStream inputStream;
    private Scanner userEnterPin;

    @Test(expected = ExitException.class)
    public void enterCommandExitTest() {
        runEnterCommand("4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void enterCommandIllegalEnterTest() {
        runEnterCommand("8");
    }

    public void runEnterCommand(String number) {
        String scannerString = number;
        inputStream = new ByteArrayInputStream(scannerString.getBytes());
        userEnterPin = new Scanner(inputStream);
        MainClass.scanner = userEnterPin;
        MainClass.enterCommand();
    }
}