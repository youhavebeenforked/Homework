package terminal.model;

public interface PinValidator {
    boolean giveAccess();
    boolean checkPin(String pin);
    void checkBlock();
}
