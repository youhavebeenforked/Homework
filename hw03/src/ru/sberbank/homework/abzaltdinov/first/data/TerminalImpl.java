package ru.sberbank.homework.abzaltdinov.first.data;

import ru.sberbank.homework.abzaltdinov.first.exception.AccountIsLockedException;
import ru.sberbank.homework.abzaltdinov.first.exception.InsufficientFundsException;
import ru.sberbank.homework.abzaltdinov.first.exception.UnsupportedCashAmountException;
import ru.sberbank.homework.abzaltdinov.first.output.ConsoleUserOutput;
import ru.sberbank.homework.abzaltdinov.first.output.UserOutput;

public class TerminalImpl implements Terminal {
    private static final long LOCK_TIME = 5000; // in milliseconds
    public static final int MAX_WRONG_PINCODES = 3;
    private static final int MIN_AVAILABLE_BILL = 100;
    private static final TerminalServer server = new TerminalServer();
    private static final PinValidator pinValidator = new PinValidator();
    private static final UserOutput out = new ConsoleUserOutput();
    private boolean isValidated = false;
    private long lockedUntil;
    private int wrongPinCodesCount = 0;
    private boolean isSessionStarted = false;
    private Integer accountNumber;

    @Override
    public boolean startSession(int accountNumber) {
        if (!isSessionStarted) {
            if (server.isAccountExists(accountNumber)) {
                isSessionStarted = true;
                this.accountNumber = accountNumber;
                return true;
            } else {
                out.println("Счет №" + accountNumber + " не найден!");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean closeSession() {
        if (isSessionStarted) {
            isSessionStarted = false;
            isValidated = false;
            lockedUntil = 0;
            wrongPinCodesCount = 0;
            accountNumber = null;
        }
        return true;
    }

    @Override
    public boolean enterPin(int pinCode) {
        try {
            checkLock();
        } catch (AccountIsLockedException ex) {
            out.println(ex.getMessage());
            return false;
        }
        if (pinValidator.validate(accountNumber, pinCode)) {
            isValidated = true;
            wrongPinCodesCount = 0;
        } else {
            out.println("Введен неверный пин-код.");
            isValidated = false;
            wrongPinCodesCount++;
            if (wrongPinCodesCount == MAX_WRONG_PINCODES) {
                out.println("Аккаунт заблокирован на " + LOCK_TIME/1000 + " секунд");
                setLock();
            }
        }
        return isValidated;
    }

    /**
     * Запрос на снятие наличных со счета.
     *
     * @param amount Сумма наличных
     * @return true - если операция успешно выполнена, иначе - false
     */
    @Override
    public boolean withdrawCash(int amount) {
        try {
            checkLock();
            checkCashAmount(amount);
            server.withdrawCashRequest(accountNumber, amount);
            out.println("С Вашего счет успешно снято " + amount + " бурлей.");
            return true;
        } catch (AccountIsLockedException ex) {
            out.println(ex.getMessage());
        } catch (InsufficientFundsException e) {
            out.println(e.getMessage());
        } catch (UnsupportedCashAmountException e) {
            out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Запрос на внесение наличных на счет.
     *
     * @param amount Сумма наличных
     * @return true - если операция успешно выполнена, иначе - false
     */
    @Override
    public boolean depositCash(int amount) {
        try {
            checkLock();
            checkCashAmount(amount);
            server.depositCashRequest(accountNumber, amount);
            out.println("На Ваш счет успешно внесено " + amount + " бурлей.");
            return true;
        } catch (AccountIsLockedException ex) {
            out.println(ex.getMessage());
        } catch (UnsupportedCashAmountException e) {
            out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void getBalance() {
        out.println("Ваш баланс " + server.getBalance(accountNumber) + " бурлей.");
    }

    public boolean isSessionStarted() {
        return isSessionStarted;
    }

    private void checkLock() throws AccountIsLockedException {
        long timeToUnlock = (lockedUntil - System.currentTimeMillis()) / 1000;
        if (timeToUnlock >= 0) {
            throw new AccountIsLockedException("Аккаунт заблокирован! Попробуйте через " + timeToUnlock + " секунд.");
        }
    }

    private void setLock() {
        lockedUntil = System.currentTimeMillis() + LOCK_TIME;
    }

    private void checkCashAmount(int amount) {
        if (amount % MIN_AVAILABLE_BILL != 0) {
            throw new UnsupportedCashAmountException("Сумма должна быть кратна " + MIN_AVAILABLE_BILL);
        }
    }

}
