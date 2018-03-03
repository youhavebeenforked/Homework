package ru.sberbank.homework.kuznecov.first;

public class TerminalImpl implements Terminal{

    private final TerminalServer server;
    private final PinValidator pinValidator;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    //Получить номер карты
    @Override
    public CardNumber getCardNumber() {
        return new CardNumber(0000_0000_0000_0000L);
    }

    @Override
    public boolean validate(CardNumber cardNumber, Pin pin) {
        return pinValidator.validate(cardNumber, pin);
    }

    @Override
    public double checkAccount(CardNumber cardNumber) {
        pinValidator.checkValidation();
        return server.getAccount(cardNumber);
    }

    @Override
    public double getCash(CardNumber cardNumber, double sum) {
        pinValidator.checkValidation();
        if (isMultiplyOfHundred(sum)){
            return server.getCash(cardNumber, sum);
        } else {
            throw new IllegalArgumentException("The sum must be a multiple of 100.");
        }
    }

    @Override
    public double putCash(CardNumber cardNumber, double sum) {
        pinValidator.checkValidation();
        if (isMultiplyOfHundred(sum)){
            return server.putCash(cardNumber, sum);
        } else {
            throw new IllegalArgumentException("The sum must be a multiple of 100.");
        }
    }

    private boolean isMultiplyOfHundred(double value){
        return Math.abs(value / 100 - (long)(value / 100)) < 0.00000001;
    }
}
