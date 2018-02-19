package ru.sberbank.homework.bobrov.terminal.term;

import ru.sberbank.homework.bobrov.terminal.exception.CheckPinException;
import ru.sberbank.homework.bobrov.terminal.exception.NotEnoughMoney;
import ru.sberbank.homework.bobrov.terminal.exception.WrongSumException;

/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 16.02.2018
 */

public interface Terminal {
    void startTerminal(long cardNumber) throws CheckPinException, WrongSumException, NotEnoughMoney;
}
