package Termianl.exeptions;

public class NetworkConnectionExeption extends RuntimeException {
    public NetworkConnectionExeption(String message) {
        super(message);
    }
}
