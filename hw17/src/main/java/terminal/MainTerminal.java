package terminal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import terminal.model.Terminal;
import terminal.writer.Writer;

@Component
public class MainTerminal {
    private final Terminal terminal;
    private final Writer writer;

    @Autowired
    public MainTerminal(Terminal terminal, Writer writer) {
        this.terminal = terminal;
        this.writer = writer;
    }

    public long checkBalance() {
        long result = 0;
        try {
            result = terminal.checkBalance();
            writer.print(String.format("Ваш баланс %s", result));
        } catch (RuntimeException e) {
            writer.print(e.getMessage());
        }
        return result;
    }

    public void putMoney(long money) {
        try {
            terminal.putMoney(money);
            writer.print(String.format("Вы положили %s на ваш счет.", money));
        } catch (RuntimeException e) {
            writer.print(e.getMessage());
        }
    }

    public long withdrawMoney(long money) {
        long result = 0;
        try {
            result = terminal.withdrawMoney(money);
            writer.print(String.format("Вы сняли %s с вашего счета.", result));
        } catch (RuntimeException e) {
            writer.print(e.getMessage());
        }
        return result;
    }

    public void enterPin(String pin) {
        try {
            if (terminal.enterPin(pin)) {
                writer.print("Пин введен верно");
            } else {
                writer.print("Неверный пин.");
            }
        } catch (RuntimeException e) {
            writer.print(e.getMessage());
        }
    }
}
