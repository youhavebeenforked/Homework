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
        if (!pinValidator.isValidated()){
            throw new NotValidatedException("At first you should type your pin. " +
                    "Please, type \"pin\" and then your pin.");
        }
        return server.getAccount(cardNumber);
    }

    @Override
    public double getCash(CardNumber cardNumber, double sum) {
        if (!pinValidator.isValidated()){
            throw new NotValidatedException("At first you should type your pin. " +
                    "Please, type \"pin\" and then your pin.");
        }
        if (Math.abs(sum / 100 - (long)(sum / 100)) < 0.00000001){
            return server.getCash(cardNumber, sum);
        } else {
            throw new IllegalArgumentException("The sum must be a multiple of 100.");
        }
    }

    @Override
    public double putCash(CardNumber cardNumber, double sum) {
        if (!pinValidator.isValidated()){
            throw new NotValidatedException("At first you should type your pin. " +
                    "Please, type \"pin\" and then your pin.");
        }
        if (Math.abs(sum / 100 - (long)(sum / 100)) < 0.00000001){
            return server.putCash(cardNumber, sum);
        } else {
            throw new IllegalArgumentException("The sum must be a multiple of 100.");
        }
    }
}
