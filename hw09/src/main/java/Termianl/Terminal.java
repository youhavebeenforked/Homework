package Termianl;

public interface Terminal {
    void setPinStorage(PinStorage pinStorage);

    boolean isCorrectPin(int pin);

    int getBankBook();

    boolean withdrawMoney(int money);

    boolean putMoney(int money);

    void setScore(int money);

}
