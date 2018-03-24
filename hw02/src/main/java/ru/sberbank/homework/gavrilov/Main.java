package ru.sberbank.homework.gavrilov;

import ru.sberbank.homework.common.Calculator;
import ru.sberbank.homework.gavrilov.utils.ConsoleInput;
import ru.sberbank.homework.gavrilov.utils.Input;

public class Main {

    /**
     * Constant fot exit.
     */
    private static final String EXIT = "quit";

    /**
     * Used input.
     */
    private final Input input;

    /**
     * Used implementation calculator.
     */
    private final Calculator calculator;

    public Main(Input input, Calculator calculator) {
        this.input = input;
        this.calculator = calculator;
    }

    /**
     * Run program.
     */
    public static void main(String[] args) {
        new Main(new ConsoleInput(), new MyCalculator()).init();
    }

    /**
     * The general loop program.
     */
    private void init() {
        String userInput;
        System.out.println("Welcome!");
        userInput = this.input.ask("Enter expression: ");
        while (!EXIT.equals(userInput)) {
            System.out.println(this.calculator.calculate(userInput));
            userInput = this.input.ask("Enter expression: ");
        }
    }

}
