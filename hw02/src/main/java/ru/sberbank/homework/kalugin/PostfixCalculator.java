package ru.sberbank.homework.kalugin;

import java.math.*;
import static  ru.sberbank.homework.kalugin.InputValidator.validate;
import static  ru.sberbank.homework.kalugin.PostfixParser.createPostfix;
import static  ru.sberbank.homework.kalugin.PostfixParser.evaluatePostfix;

import ru.sberbank.homework.common.Calculator;

/**
 * Имплементация калькулятора на основе постифксных матем. выражений.
 */
public class PostfixCalculator implements Calculator {

    private static final String QUIT_COMMAND = "quit";

	private static String result;

	public String calculate(String userInput) {
        if (userInput.equals(QUIT_COMMAND) || (userInput == null)) return null;
		// проверяем выражение
		userInput = validate(userInput, result);
		if (userInput.startsWith("error"))
			return userInput;

		// создаем постфикс строку из ввода
		String postfixEquation = createPostfix(userInput);
		// решаем выражение в постфиксном виде
		Double answer = evaluatePostfix(postfixEquation);
		// подготавливаем результат
		result = prepareResult(answer);

		return result;
	}

	private String prepareResult(Double answer) {
        // округляем до 2-х знаков после запятой
		answer = new BigDecimal(answer).setScale(2, RoundingMode.HALF_UP).doubleValue();
		// в случае если после округления число оканчивается только нулями после точки,
        // уберем и их из ответа
		if ((answer - Math.round(answer)) != 0) {
			return String.valueOf(answer);
		}
		else {
			return String.valueOf(Math.round(answer));
		}
	}
}

