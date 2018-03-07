package Termianl;

import Termianl.exeptions.*;

public class TerminalImpl implements Terminal {
    private final TerminalServer server = new TerminalServer();
    private final PinValidator pinValidator = new PinValidator();
    private final MoneyValidator moneyValidator = new MoneyValidator();
    private final int MAX_INCORRECT_ATTEMP = 3;
    private final int TIME_BLOCK = 5000; //mlsec
    private long startTime = 0L;
    private long currentTime = 0L;
    private boolean correctPin = false;
    private int currentIncorrectAttemp = 0;
    private boolean time = true;

    @Override
    public boolean setPinStorage(PinStorage pinStorage) {
        server.setPinStorage(pinStorage);
        return true;
    }

    @Override
    public boolean setPin(int pin) {
        unblock();
        if (correctPin) {
            System.out.println("You already entered a valid pin");
            return true;
        }
        try {
            if (!time) {
                throw new AccountIsLockedException("Your account is locked, please try again after:" +
                        (TIME_BLOCK - (int) (currentTime - startTime)) / 1000 + " seconds");
            }
            if (currentIncorrectAttemp < MAX_INCORRECT_ATTEMP) {
                pinValidator.validPin(pin);
                server.checkPin(pin);
                correctPin = true;
                currentIncorrectAttemp = 0;
                System.out.println("Access");
                return true;
            }
        } catch (ValidatePinExeption | NotCorrectPinExeption  e) {
            System.out.println(e.getMessage());
            currentIncorrectAttemp++;
            if (currentIncorrectAttemp >= MAX_INCORRECT_ATTEMP) {
                currentIncorrectAttemp = 0;
                block();
            }
            return false;
        } catch (NetworkConnectionExeption | AccountIsLockedException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    @Override
    public int getBankBook() {
        try {
            isCorrectPin();
            System.out.println(server.getBankBook());
            return server.getBankBook();
        } catch (NetworkConnectionExeption | LoggedExeption e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public boolean withdrawMoney(int money) {
        try {
            isCorrectPin();
            moneyValidator.validMoney(money);
            server.withdrawMoney(money);
        } catch (NetworkConnectionExeption | ValidateMoneyExeption | LoggedExeption | InsufficientFundsExeption e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean putMoney(int money) {
        try {
            isCorrectPin();
            moneyValidator.validMoney(money);
            server.putMoney(money);
        } catch (NetworkConnectionExeption | ValidateMoneyExeption | LoggedExeption e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private void isCorrectPin() {
        if (!correctPin) {
            throw new LoggedExeption();
        }
    }

    private void block() {
        time = false;
        startTime = System.currentTimeMillis();
    }

    private void unblock() {
        currentTime = System.currentTimeMillis();
        if (currentTime - startTime >= TIME_BLOCK) {
            time = true;
        }
    }
}

