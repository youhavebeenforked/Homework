package ru.sberbank.homework.bobrov.terminal;

import ru.sberbank.homework.bobrov.terminal.exception.IncorrectPinException;

/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 16.02.2018
 */

public interface Terminal {
    void startTerminal(long cardNumber) throws IncorrectPinException;
}
