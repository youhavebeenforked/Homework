package ru.sberbank.homework.dergun.hw1;

public class TerminalImpl implements Terminal {
    private final TerminalServer server = new TerminalServer();
    private final PinValidator pinValidator = new PinValidator();
    private final MoneyValidator moneyValidator = new MoneyValidator();
    private long startTime = 0L;
    private long currentTime = 0L;
    private boolean correctPin = false;
    private int numberIncorrect = 0;
    private boolean time = true;

    @Override
    public void setPin(int pin) {
        unblock();
        if (correctPin) {
            System.out.println("You already entered a valid pin");
            return;
        }
        try {
            if (!time) {
                throw new AccountIsLockedException("Your account is locked, please try again after:" +
                        (5 - (int) ((currentTime - startTime) / 1000)) + " seconds");
            }
            if (numberIncorrect < 3 && pinValidator.validPin(pin) && server.checkPin(pin)) {
                correctPin = true;
                numberIncorrect = 0;
                System.out.println("Access");
            }
        } catch (FailedValidatePin | NotCorrectPin e) {
            System.out.println(e.getMessage());
            numberIncorrect++;
            if (numberIncorrect >= 3) {
                numberIncorrect = 0;
                block();
            }
        } catch (NetworkConnectionProblem | AccountIsLockedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getBankBook() {
        try {
            if (isCorrectPin()) {
                System.out.println(server.getBankBook());
                return server.getBankBook();
            }
        } catch (NetworkConnectionProblem | NotLoggedIn e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public void withdrawMoney(int money) {
        try {
            if (isCorrectPin() && moneyValidator.validWithdrawMoney(money)) {
                server.withdrawMoney(money);
            }
        } catch (NetworkConnectionProblem | FailedValidateMoney | NotLoggedIn | InsufficientFunds e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void putMoney(int money) {
        try {
            if (isCorrectPin() && moneyValidator.validPutMoney(money)) {
                server.putMoney(money);
            }
        } catch (NetworkConnectionProblem | FailedValidateMoney | NotLoggedIn e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isCorrectPin() {
        if (correctPin) {
            return true;
        }
        throw new NotLoggedIn();
    }

    private void block() {
        time = false;
        startTime = System.currentTimeMillis();
    }

    private void unblock() {
        currentTime = System.currentTimeMillis();
        if (currentTime - startTime >= 5000) {
            time = true;
        }
    }
}

class AccountIsLockedException extends RuntimeException {
    AccountIsLockedException(String message) {
        super(message);
    }
}

class NotLoggedIn extends RuntimeException {
    NotLoggedIn() {
        super("Not logged in.");
    }
}
