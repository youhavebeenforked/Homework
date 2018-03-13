package Termianl.exeptions;

public class ValidateMoneyExeption extends RuntimeException {
    public ValidateMoneyExeption(String message) {
        super(message);
    }
}
