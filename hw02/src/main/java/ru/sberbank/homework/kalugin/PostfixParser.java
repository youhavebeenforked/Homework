package  ru.sberbank.homework.kalugin;

import java.util.*;

/**
 * Работа с математическими выражениями в postfix notation (a.k.a польская запись).
 */
class PostfixParser {
    /** Приведет матем. выражения вида "1+2*3" "2*(3+5)" к "1 2 + 3 *" "2 3 5 + *"
     *  
     *  Алгоритм работы:
     *  Число передается сразу в строку-результат.
     *  Для текущего матем. оператора проверяем - есть-ли на стеке другой оператор,
     *  с приоритетом => текущего
     *  если есть - его в строку-результат, текущий на стек.
     *  Открытая скобка '('- сразу в стек.
     *  Закрытая ')' - все из стека в результат, пока не дойдем до открытой, 
     *  сами скобки отбрасываем.
     *  
     */
    static String createPostfix(String s) {
        Stack stack = new Stack<String>();
        StringBuilder resultStr = new StringBuilder();
        char c;
        int strPntr = 0;

        while (strPntr < s.length()) {
            c = s.charAt(strPntr);
            if (c == ')') {
                // пока на стеке не встретим "(" добавляем в строку ответа содержимое стека
                while (!stack.empty() && !stack.peek().equals('(')) {
                    resultStr.append(stack.pop());
                }
                if (!stack.empty())
                    stack.pop();
            }
            else if (c == '+' || c == '-') {
                // Работаем с оператором '+'. Если на стеке уже есть какой-либо 
                // матем. оператор c равным ('+' '-') и большим ('*' '/') приоритетом 
                // добавим его
                if (!stack.empty() && (stack.peek().equals('+') || stack.peek().equals('-') ||
                   stack.peek().equals('/') || stack.peek().equals('*'))) {
                    resultStr.append(stack.pop());
                }
                stack.push(c);
            }
            // операторы '*' и '/' приоритетны над '+' и '-',
            // потому '+' и '-' на стеке не ищем
            else if (c == '*' || c == '/') {
                if (!stack.empty() && (stack.peek().equals('*') || stack.peek().equals('/'))) {
                    resultStr.append(stack.pop());
                }
                stack.push(c);
            }
            else if (c == '(') {
                stack.push(c);
            }
            // если нашли цифру
            else if (c >= '0' && c <= '9') {
                // смотрим дальше в строку - ищем еще цифры, составляем из них число
                int beg = strPntr;
                while (strPntr < s.length()-1) {
                    char с = s.charAt(strPntr+1);
                    if ( с != '.' || c < '0' && c > '9') {
                        break;
                    }
                    strPntr += 1;
                }
                resultStr.append(s.substring(beg,strPntr+1));
            }
            else {
                throw new IllegalArgumentException("Invalid symbol: " + c);
            }

            strPntr++;
            // пробел между элементами выражения
            resultStr.append(" ");
        }
        // в крайних случаях на стеке могоут остаться элементы, проверим и добавим
        while (!stack.empty()) {
            resultStr.append(stack.pop());
            resultStr.append(" ");
        }
        // удалим пробел конца строки добавленный в одном из циклов выше
        resultStr.deleteCharAt(resultStr.length()-1);

        return resultStr.toString();
    }

    /** 
    *   Решит постфиксные выражения вида "1 2 + 3 *", вернет double 
    *   Алгоритм:
    *   Идем по переданной строке, числа сразу на стек, дошли до оператора -
    *   вынули два верних числа со стека, решили, результат на стек.
    */
    static double evaluatePostfix(String s) {
        Stack<Double> stack = new Stack<Double>();
        int strPntr = 0;
        char c;
        while (strPntr < s.length()) {
            c = s.charAt(strPntr);
            if (c == '+') {
                compute(stack, Operation.ADD);
            }
            else if (c == '-') {
                compute(stack, Operation.SUBTRACT);
            }
            else if (c == '*') {
                compute(stack, Operation.MULTIPLY);
            }
            else if (c == '/') {
                compute(stack, Operation.DIVIDE);
            }
            // работа с числами
            else if (c >= '0' && c <= '9') {
                int beg = strPntr;
                while (strPntr < s.length()) {
                    if (s.charAt(strPntr+1) != ' ') {
                        break;
                    }
                    strPntr += 1;
                }
                int end = strPntr+1;
                try {
                    stack.push(Double.parseDouble(s.substring(beg, end)));
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("String to number parsing exception: " + s);
                }
            }
            strPntr++;
        }
        return stack.pop();
    }

    private static void compute(Stack<Double> s, Operation o) {
        double x1 = Double.valueOf(s.pop().toString());
        double x2 = Double.valueOf(s.pop().toString());
        double x = o.getFunction().applyAsDouble(x2, x1);
        s.push(x);
    }
}