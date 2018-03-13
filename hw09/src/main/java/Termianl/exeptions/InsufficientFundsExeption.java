package Termianl.exeptions;

public class InsufficientFundsExeption extends RuntimeException {
    public InsufficientFundsExeption(String message) {
        super(message);
    }
}
