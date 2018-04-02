package ru.sberbank.homework.maruev.hw3_1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sberbank.homework.maruev.hw3_1.constants.BankCommands;
import ru.sberbank.homework.maruev.hw3_1.exceptions.LockException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Иван.
 */
public class TerminalImplTest {
    private String PIN = "0011";
    private String incorrectPIN = "1111";
    private int balance = 2000;
    private int sum;
    private String scannerString;
    private PinValidator validator = new PinValidator();
    private TerminalImpl terminal = new TerminalImpl();
    private TerminalServer server = new TerminalServer();
    private ByteArrayInputStream inputStream = new ByteArrayInputStream(PIN.getBytes());
    private OutputStream outputStream = new ByteArrayOutputStream();
    private Scanner userEnterPin = new Scanner(inputStream);


    @Before
    public void setUp() {
        server.setBalance(balance);
        validator.setPinCode(PIN);
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void returnConditions() {
        System.setOut(null);
    }

    @Test
    public void checkMoneyTest() {
        terminal.checkBalance(server, userEnterPin);

        String actual = outputStream.toString().trim();
        String expected = BankCommands.PIN_CODE + "\r\n"
                + BankCommands.BALANCE_SUM
                + server.getBalance();

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = LockException.class)
    public void checkMoneyLockTest() {
        setPinConditions(incorrectPIN);
        terminal.checkBalance(server, userEnterPin);
    }

    @Test
    public void getMoneyTest() {
        setSumConditions(100);
        setPinConditions(scannerString);

        terminal.getMoney(server, userEnterPin);

        int expected = balance - sum;
        int actual = server.getBalance();

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getMoneyIncorrectTest() {
        setSumConditions(55);
        setPinConditions(scannerString);
        terminal.getMoney(server, userEnterPin);
    }

    @Test(expected = LockException.class)
    public void getMoneyLockTest() {
        setPinConditions(incorrectPIN);
        terminal.getMoney(server, userEnterPin);
    }

    @Test
    public void setMoneyTest() {
        setSumConditions(100);
        setPinConditions(scannerString);

        terminal.setMoney(server, userEnterPin);

        int expected = balance + sum;
        int actual = server.getBalance();

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMoneyIncorrectTest() {
        setSumConditions(55);
        setPinConditions(scannerString);

        terminal.setMoney(server, userEnterPin);
    }

    @Test(expected = LockException.class)
    public void setMoneyLockTest() {
        setPinConditions(incorrectPIN);
        terminal.setMoney(server, userEnterPin);
    }

    public void setSumConditions(int newSum) {
        sum = newSum;
        scannerString = PIN + "\n" + sum;
    }

    public void setPinConditions(String PIN) {
        inputStream = new ByteArrayInputStream(PIN.getBytes());
        userEnterPin = new Scanner(inputStream);
    }
}