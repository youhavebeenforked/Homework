package ru.sberbank.homework.kudryavykh;

public class TerminalImpl implements Terminal, Cloneable {

    public boolean correctPin;
    private MessageTerminal message;
    private TerminalServer server;
    private final int PIN_LENGTH = 4;

    public TerminalImpl(long cardNumber) {
        this.message = new MessageTerminal();
        try {
            this.server = new TerminalServer(cardNumber);
        } catch (InvalidCardNumber ex) {
            ex.printMessage();
        }
    }

    public TerminalImpl(Account account) {
        this.message = new MessageTerminal();
        this.server = new TerminalServer(account);
    }

    public TerminalImpl(TerminalImpl terminal) {
        this.message = terminal.message;
        this.server = terminal.server;
        this.correctPin = terminal.correctPin;


    }

    // Проверка только лишь на количество символов.
    // Ведь пользователь в банкомате не может ввести буквы, верно?
    @Override
    public boolean pin() {
        try {
            String pin = message.inputPin();
            if (pin.length() == PIN_LENGTH) {
                correctPin = server.pinCheck(Short.parseShort(pin));
                if (!correctPin) {
                    message.incorrectPinValue();
                }
                return correctPin;
            } else {
                //Кидать исключение и сразу его ловить - фиговое решение, но суть задания - продемонстрировать понимание...
                //...exceptionов, потому оставил.
                try {
                    throw new IncorrectPinCode("Pin код не правильный");
                } catch (IncorrectPinCode ex) {
                    message.incorrectPinValue();
                }
                return false;
            }
        } catch (AccountIsLockedException ex) {
            message.accountLocked();
        }
        return false;
    }

    private boolean validationInputSum(int userAnswers) throws AccessAccoutException {
        if (!correctPin) {
            throw new AccessAccoutException("Вызов метода невозможен. Доступ к аккаунту не получен.");
        } else {
            if (userAnswers % 100 != 0) {
                message.incorrectInputValue();
                return false;
            }
        }
        return true;
    }

    @Override
    public void getMoney() throws AccessAccoutException {
        int usersSum = message.inputSum();
        if(validationInputSum(usersSum)) {
            try {
                server.setBalance(server.getBalance() - usersSum);
            } catch (IncorrectBalanceException e) {
                message.incorrectBalanceValue();
            }
        }
    }

    @Override
    public void setMoney() throws AccessAccoutException {
        int usersSum = message.inputSum();
        if(validationInputSum(usersSum)) {
            try {
                server.setBalance(server.getBalance() + usersSum);
            } catch (IncorrectBalanceException e) {
                message.incorrectBalanceValue();
            }
        }
    }

    @Override
    public void checkAccount() throws AccessAccoutException {
        if (!correctPin) {
            throw new AccessAccoutException("Вызов метода невозможен. Доступ к аккаунту не получен.");
        } else {
            message.balanceCash(server.getBalance());
        }
    }
}

class IncorrectSumValue extends Exception {

    public IncorrectSumValue(String message) {
        super(message);
    }
}

class AccessAccoutException extends Exception {

    public AccessAccoutException(String message) {
        super(message);
    }
}

class IncorrectPinCode extends Exception {

    public IncorrectPinCode(String message) {
        super(message);
    }

}

