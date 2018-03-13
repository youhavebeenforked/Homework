package Termianl.exeptions;

public class LoggedExeption extends RuntimeException {
    public LoggedExeption() {
        super("Not logged in.");
    }
}
