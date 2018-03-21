package  ru.sberbank.homework.kalugin;

import java.util.*;

/**
 * Работа с математическими выражениями в postfix notation (a.k.a польская запись).
 */
class PostfixParser {
    /** Приведет матем. выражения вида "1*2+3" к "1 2 * 3 +"
     *
     *  Алгоритм работы используя стек:
     *  Число передается сразу в результат.
     *  Для текущего матем. оператора проверяем - есть ли на стеке другой оператор,
     *  с приоритетом => текущего.
     *  Если есть - его в результат, текущий на стек.
     *  Если к концу на стеке остаются операторы - добавим их в результат.
     */

    List<Element> createPostfix(List<Element> equation) {
        Deque<Element> stack = new ArrayDeque<>();
        List<Element> result = new ArrayList<>();

        for (Element e : equation) {
            if (e.isNumber()) {
                result.add(e);
            }
            else {
                if (e.getElement() == Operation.MULTIPLY
                        || e.getElement() == Operation.DIVIDE) {
                    if (stack.size() > 0) {
                        if (stack.peek().getElement() == Operation.MULTIPLY
                                || stack.peek().getElement() == Operation.DIVIDE) {
                            result.add(stack.pop());
                        }
                    }
                }
                else {
                    if (stack.size() > 0) {
                        result.add(stack.pop());
                    }
                }
                stack.push(e);
            }
        }
        while (stack.size() > 0) {
            result.add(stack.pop());
        }
        return result;
    }

    double evaluatePostfix(List<Element> equation) {
        Deque<Double> stack = new ArrayDeque<>();
        double x = 0;

        for (Element e : equation) {
            if (e.isNumber()) {
                stack.push((Double) e.getElement());
            }
            else {
                double x1 = stack.pop();
                double x2 = stack.pop();
                Operation o = (Operation) e.getElement();
                x = o.compute(x2, x1);
                stack.push(x);
            }
        }
        return x;
    }

    double compute(List<Element> equation) {
        equation = createPostfix(equation);
        return evaluatePostfix(equation);
    }
}