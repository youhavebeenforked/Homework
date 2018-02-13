package ru.sberbank.homework.dergun.hw1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TerminalImpl terminal = new TerminalImpl();
        terminal.setPin(45888);
        terminal.setPin(999);
        terminal.setPin(4567);
        terminal.setPin(4567);
        terminal.setPin(4567);
        terminal.setPin(4567);
        terminal.setPin(4567);
        Thread.sleep(2000);
        terminal.setPin(4567);
        Thread.sleep(2000);
        terminal.setPin(4567);
        Thread.sleep(2000);
        terminal.setPin(4567);
        terminal.setPin(4875);
        terminal.setPin(4567);
        terminal.getBankBook();
        terminal.withdrawMoney(200);
        terminal.getBankBook();
        terminal.withdrawMoney(10000);
        terminal.getBankBook();
        terminal.withdrawMoney(10);
        terminal.getBankBook();
        terminal.withdrawMoney(100);
        terminal.getBankBook();
        terminal.putMoney(10000);
        terminal.getBankBook();
    }
}
