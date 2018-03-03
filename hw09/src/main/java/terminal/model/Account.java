package terminal.model;

public interface Account {
    long checkBalance();
    long withdrawMoney(long money);
    void putMoney(long money);
}
