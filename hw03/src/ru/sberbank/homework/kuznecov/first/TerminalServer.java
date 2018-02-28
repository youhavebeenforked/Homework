package ru.sberbank.homework.kuznecov.first;

public class TerminalServer {

    //это поле - модель записи в базе данных
    private double current;

    TerminalServer() {
    }

    TerminalServer(double current) {
        this.current = current;
    }

    public double getAccount(CardNumber cardNumber) {
        return current;
    }

    double getCash(CardNumber cardNumber, double sum){
        if (current - sum < 0) {
            throw new NotEnoughMoneyException("Not enough money on your Account.");
        } else {
            current -= sum;
            return current;
        }
    }

    double putCash(CardNumber cardNumber, double sum){
        current += sum;
        return current;
    }
}
