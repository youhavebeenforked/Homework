package terminal.model;

import org.springframework.beans.factory.annotation.Autowired;
import terminal.exception.NotAuthorizedException;
import terminal.exception.NumberException;


public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;

    @Autowired
    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    @Override
    public long checkBalance() {
        pinValidator.checkBlock();
        if (pinValidator.giveAccess()) {
            return server.checkBalance();
        } else {
            throw new NotAuthorizedException("Вы не авторизованы");
        }
    }

    @Override
    public void putMoney(long money) {
        checkMultiple(money);
        pinValidator.checkBlock();
        if (pinValidator.giveAccess()) {
            server.putMoney(money);
        } else {
            throw new NotAuthorizedException("Вы не авторизованы");
        }
    }

    @Override
    public long withdrawMoney(long money) {
        checkMultiple(money);
        pinValidator.checkBlock();
        if (pinValidator.giveAccess()) {
            return server.withdrawMoney(money);
        } else {
            throw new NotAuthorizedException("Вы не авторизованы");
        }
    }

    @Override
    public boolean enterPin(String pin) {
        pinValidator.checkBlock();
        return pinValidator.checkPin(pin);
    }

    private void checkMultiple(long money) {
        if (money == 0) {
            throw new NumberException("Введено число 0.");
        }
        if (money % 100 != 0) {
            throw new NumberException("Введено число не кратное 100. Введите кратное 100");
        }
    }
}
