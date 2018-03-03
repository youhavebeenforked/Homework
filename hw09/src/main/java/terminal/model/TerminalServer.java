package terminal.model;

public interface TerminalServer {
    long checkBalance();
    void putMoney(long money);
    long withdrawMoney(long money);
}
