package ru.sberbank.homework.dergun;

public class Main {
    public static void main(String[] args) {
        int a = 0xff_ff;
        long b = +0xB101L;
        System.out.println("Welcome to Simple!");
        final ProxyCalculator proxyCalculator = new ProxyCalculator();
        final ScannerCalculator scannerCalculator = new ScannerCalculator(proxyCalculator, System.in, System.out);
        scannerCalculator.start();
    }
}