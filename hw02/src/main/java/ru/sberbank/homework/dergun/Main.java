package ru.sberbank.homework.dergun;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Simple!");
        final EngineCalculatorImpl calculatorImpl = new EngineCalculatorImpl();
        final ProxyCalculator proxyCalculator = new ProxyCalculator();
        final ScannerCalculator scannerCalculator = new ScannerCalculator(proxyCalculator, System.in, System.out);
        scannerCalculator.start();
    }
}