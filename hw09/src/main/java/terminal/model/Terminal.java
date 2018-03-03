package terminal.model;

public interface Terminal {
    long checkBalance();
    void putMoney(long money);
    long withdrawMoney(long money);
    boolean enterPin(String pin);
}
