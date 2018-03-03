package ru.sberbank.homework.gavrilov.hw03_1;

import ru.sberbank.homework.gavrilov.hw03_1.exceptions.*;
import ru.sberbank.homework.gavrilov.hw03_1.ui.ConsoleUserInterfaceMessages;
import ru.sberbank.homework.gavrilov.hw03_1.ui.UserInterfaceMessages;

public class Atm implements Terminal {

    private static final int COINS = 100;

    private Input input;
    private final UserInterfaceMessages uim;
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private String cardNumber;
    private boolean isAllowAccess = false;
    private int countAttemptPin = 0;

    public Atm() {
        this.server = new TerminalServer();
        this.pinValidator = new PinValidator();
        this.input = new ConsoleInput();
        this.uim = new ConsoleUserInterfaceMessages();
    }

    @Override
    public boolean startTerminal(String cardNumber) {
        int pin;
        try {
            if (pinValidator.checkNumberCard(cardNumber)) {
                this.cardNumber = cardNumber;
            }
        } catch (NotExistAccountException e) {
            uim.showAccountMessage(e.getMessage());
            System.exit(-1);
        }
        while (true) {
            try {
                pin = input.inputInt("Введите пин код:");
                checkPinCode(pin);
                isAllowAccess = true;
                server.startTerminal(this.cardNumber);
                return true;
            } catch (CheckPinCodeException | NumberFormatException e) {
                uim.showPinMessage(e.getMessage());
                ++countAttemptPin;
            } catch (NetworkProblemException e) {
                uim.showNetworkMessage(e.getMessage());
                System.exit(-1);
            }
            if (countAttemptPin == 3) {
                try {
                    throw new AccountIsLockedException("Аккаунт заблокирован на 5 сек!");
                } catch (AccountIsLockedException e) {
                    uim.showAccountMessage(e.getMessage());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    countAttemptPin = 0;
                }
            }
        }
    }

    @Override
    public int checkBalance() {
        if (!isAllowAccess) throw new AccessAtmException("Access is denied!");
        return server.checkBalance();
    }

    @Override
    public void depositMoney(int amount) throws MoneyException {
        if (!isAllowAccess) throw new AccessAtmException("Access is denied!");
        if (amount % COINS == 0) {
            server.depositMoney(amount);
        } else throw new WrongSumException("Введена сумма не кратная " + COINS + ".");
    }

    @Override
    public int withDrawMoney(int amount) throws MoneyException, CheckPinCodeException {
        if (!isAllowAccess) throw new AccessAtmException("Access is denied!");
        if (amount % COINS == 0) {
            return server.withDrawMoney(amount);
        } else throw new WrongSumException("Введена сумма не кратная 100.");
    }

    private boolean checkPinCode(int pinCode) throws CheckPinCodeException {
        return pinValidator.isValidPin(cardNumber, pinCode);
    }
}
