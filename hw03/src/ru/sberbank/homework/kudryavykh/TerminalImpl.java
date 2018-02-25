package ru.sberbank.homework.kudryavykh;

public class TerminalImpl implements Terminal{

    public boolean correctPin = false;

    private MessageTerminal message;
    private TerminalServer server;

    public TerminalImpl(long cardNumber) {
        try {
            this.server = new TerminalServer(cardNumber);
        }
        catch (InvalidCardNumber ex) {
            message.invalidCardNumber();
            System.exit(1);
        }
        this.message = new MessageTerminal();
    }

    // Проверка только лишь на количество символов.
    // Ведь пользователь в банкомате не может ввести буквы, верно?
    public boolean Pin() {
        try {
            String pin = message.inputPin();
            if(pin.length() == 4) {
                correctPin = server.pinCheck(Short.parseShort(pin));
                if(!correctPin) {
                    message.incorrectPinValue();
                }
                return correctPin;
            }
            else {
                //Кидать исключение и сразу его ловить - фиговое решение, но суть задания - продемонстрировать понимание...
                //...exceptionов, потому оставил.
                try {
                    throw new IncorrectPinCode("Pin код не правильный");
                }
                catch (IncorrectPinCode ex) {
                    message.incorrectPinValue();
                }
                return false;
            }
        }
        catch (AccountIsLockedException ex) {
            message.accountLocked();
        }
        return false;
    }

    public void setMoney() throws AccessAccoutException {
        if(!correctPin) {
            throw new AccessAccoutException("Вызов метода невозможен. Доступ к аккаунту не получен.");
        }
        else {
            try {
                int userAnswers = message.inputSum();
                if (userAnswers % 100 == 0) {
                    server.setBalance(server.getBalance() + userAnswers);
                } else {
                    message.incorrectInputValue();
                }
            }
            catch (IncorrectBalanceException e) {
                //Логика по обработке неправильного баланса
                //В теории исключения тут никогда не будет т.к. идет увеличение баланса
                //Надо ли обрабатывать?
            }
        }
    }

    public void getMoney() throws AccessAccoutException {
        if(!correctPin) {
            throw new AccessAccoutException("Вызов метода невозможен. Доступ к аккаунту не получен.");
        }
        else {
            try {
                int userAnswers = message.inputSum();
                if (userAnswers % 100 == 0) {
                    server.setBalance(server.getBalance() - userAnswers);
                } else {
                    message.incorrectInputValue();
                }
            }
            catch (IncorrectBalanceException e) {
                message.incorrectBalanceValue();
            }
        }
    }

    public void checkAccount() throws AccessAccoutException {
        if(!correctPin) {
            throw new AccessAccoutException("Вызов метода невозможен. Доступ к аккаунту не получен.");
        }
        else {
            message.getBalanceCash(server.getBalance());
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

