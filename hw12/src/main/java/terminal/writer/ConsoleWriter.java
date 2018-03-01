package terminal.writer;

public class ConsoleWriter implements Writer {

    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
