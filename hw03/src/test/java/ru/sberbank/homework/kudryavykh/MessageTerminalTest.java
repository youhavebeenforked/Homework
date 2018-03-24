package ru.sberbank.homework.kudryavykh;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MessageTerminalTest {
    MessageTerminal terminalTest = new MessageTerminal();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void incorrectPinValue() {
        terminalTest.incorrectPinValue();
        String result = outputStream.toString();
        result = result.substring(0, result.length() - 2);
        assertEquals("Некорректный Pin код. Попробуйте еще раз!", result);
    }

    @Test
    public void incorrectBalanceValue() {
        terminalTest.incorrectBalanceValue();
        String result = outputStream.toString();
        result = result.substring(0, result.length() - 2);
        assertEquals("Ваш баланс не может быть отрицательным!", result);
    }

    @Test
    public void incorrectInputValue() {
        terminalTest.incorrectInputValue();
        String result = outputStream.toString();
        result = result.substring(0, result.length() - 2);
        assertEquals("Сумма должна быть кратна 100.", result);
    }

    @Test
    public void invalidCardNumber() {
        terminalTest.invalidCardNumber();
        String result = outputStream.toString();
        result = result.substring(0, result.length() - 2);
        assertEquals("Карта повреждена или не обслуживается банком.", result);
    }

    @Test
    public void accountLocked() {
        terminalTest.accountLocked();
        String result = outputStream.toString();
        result = result.substring(0, result.length() - 2);
        assertEquals("Слишком большое количество неверных попыток ввода Pin кода. " +
                "Аккаунт заблокирован", result);
    }

    @Test
    public void inputSum() {
        System.setIn(new ByteArrayInputStream("7777".getBytes()));
        System.setIn(System.in);
        int result = terminalTest.inputSum();
        assertEquals(7777, result);
    }

    @Test
    public void inputPin() {
        System.setIn(new ByteArrayInputStream("7777".getBytes()));
        System.setIn(System.in);
        String result = terminalTest.inputPin();
        assertEquals("7777", result);
    }

    @Test
    public void balanceCash() {
        terminalTest.balanceCash(543);
        String result = outputStream.toString();
        result = result.substring(0, result.length() - 2);
        assertEquals("Ваш баланс " + 543.0, result);
    }

    @Test
    public void menuMessage() {
//        MessageTerminal.menuMessage();
//        String expected = "1 -> Посмотреть баланс\n2 -> Снять со счета\n3 -> Положить на счет\n4 -> Выход";
//        String result = outputStream.toString();
//        result = result.substring(0, result.length() - 2);
//        assertEquals(expected, result);
    }
}