package ru.sberbank.homework.bobrov.terminal;


import ru.sberbank.homework.bobrov.terminal.exception.IncorrectPinException;

import java.util.Scanner;

/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 16.02.2018
 */


public class TerminalImpl implements Terminal {
    private final TerminalServer server = new TerminalServerImpl();
    private final PinValidator pinValidator = new PinValidator();

    @Override
    public void startTerminal(long cardNumber) throws IncorrectPinException {
        Integer pinCode;
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your pin code");
        pinCode = scanner.nextInt();
        if (String.valueOf(pinCode).length() == 4) {
            if (pinValidator.checkCredentials(cardNumber, pinCode)) {
                //do something
            } else {
                throw new IncorrectPinException("Wrong Pin!");
            }
        } else {
            throw new IncorrectPinException("Please enter only 4 digit");
        }


    }


}
