package ru.sberbank.homework.gavrilov.hw03_1;

public interface Input {

    /**
     * Запрашиваем у пользователя ввод данных.
     *
     * @param ask вопрос пользователю.
     * @return строковое представление пользовательского ввода.
     */
    String input(String ask);

    default int inputInt(String ask) throws NumberFormatException {
        return Integer.parseInt(input(ask));
    }

}
