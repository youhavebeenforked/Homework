package terminal.exception;

public class ServerProblemException extends RuntimeException {
    public ServerProblemException(String message) {
        super(message);
    }
}
