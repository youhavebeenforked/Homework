package ru.sberbank.homework.gavrilov.utils;

import ru.sberbank.homework.gavrilov.operation.*;

import java.util.Locale;

public class Helper {

    /**
     * Arithmetic operations of Enum.
     */
    private EnumOperation enumOperation;

    /**
     * The slitted user expression.
     */
    private String[] usersCommand;

    /**
     * Checking command is valid.
     * It should not process commands other than expressions a_@_b и @_b
     * If an incorrect command is entered,
     * there should be a value "error > wrong expression"
     *
     * @return true, if entered command a_@_b и @_b otherwise - false;
     */
    public boolean isValidCommand(String userInput) {
        if (userInput == null) {
            return false;
        }
        usersCommand = userInput.trim().toLowerCase(Locale.ENGLISH).split(" ");
        if (usersCommand.length == 2) {
            return setValidOperation(usersCommand[0]);
        }
        return usersCommand.length == 3 && setValidOperation(usersCommand[1]);
    }

    /**
     * Setting is validate operation.
     * If contained the operation is in EnumOperation, then the operation is valid .
     *
     * @param operation the symbol enumOperation.
     * @return true if enumOperation exist on ENUM EnumOperation, otherwise - false.
     */
    private boolean setValidOperation(String operation) {
        for (EnumOperation enumOperation : EnumOperation.values()) {
            if (enumOperation.getOperation() == operation.charAt(0)) {
                this.enumOperation = enumOperation;
                return true;
            }
        }
        return false;
    }

    /**
     * Return entered user command.
     *
     * @return the string array of splitted user expression.
     */
    public String[] getUsersCommand() {
        return usersCommand;
    }

    public Operation execute() {
        switch (enumOperation) {
            case ADDITION:
                return new Addition();
            case SUBTRACT:
                return new Subtraction();
            case MULTIPLY:
                return new Multiplication();
            case DIVIDE:
                return new Division();
            default:
                throw new UnsupportedOperationException("Not exist the operation!");
        }
    }
}
