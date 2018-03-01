package terminal.exception;

public class AccountIsLockedException extends RuntimeException {
    public AccountIsLockedException(String message) {
        super(message);
    }
}
