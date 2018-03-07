package Termianl;

public interface Terminal {
    boolean setPinStorage(PinStorage pinStorage);

    boolean setPin(int pin);

    int getBankBook();

    boolean withdrawMoney(int money);

    boolean putMoney(int money);

}
