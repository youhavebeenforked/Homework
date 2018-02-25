package ru.sberbank.homework.kalugin;

public class Main {
    private void runTesting() {
        SimpleCalculatorTest test = new SimpleCalculatorTest();

        test.additionTest(1, 3, 4);
        test.additionTest(2.365, 5.678, 8.043);

        test.subtractionTest(5, 2, 3);
        test.subtractionTest(7.8, 2.5, 5.3);

        test.multiplicationTest(10, 8, 80);
        test.multiplicationTest(5.7, 2.8, 15.96);

        test.divisionTest(50, 5, 10);
        test.divisionTest(50, 4, 12.5);
    }

    public static void main(String[] args) {
        new Main().runTesting();
    }
}
