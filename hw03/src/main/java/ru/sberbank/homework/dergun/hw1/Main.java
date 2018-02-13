package ru.sberbank.homework.dergun.hw1;

public class Main {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        try {
            Terminal terminal = new TerminalImpl();
            terminal.setPinStorage(() -> 4875);
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
