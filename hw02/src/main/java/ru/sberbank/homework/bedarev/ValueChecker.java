package ru.sberbank.homework.bedarev;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueChecker {

    private final static String OPERAND_PATTERN = "((?:[-+])?(?:(?:0[0-7_]+)|" +
            "(?:0b[0-1][0-1_]*)|" +
            "(?:0x[0-9a-f][0-9a-f_]*)|" +
            "(?:[0-9])|" +
            "(?:[1-9][\\d_]*[\\d])|" +
            "(?:\\d.\\d)|" +
            "(?:\\d(?:[\\d_]*\\d)*\\.\\d(?:[\\d_]*\\d)*))(?:[lfd])?)";

    private final static String OPERATOR_PATTERN = "([*+\\-/])";


    public List<String> checkBinaryOperation(String cmd) {
        String[] derrivedExpr = cmd.split(" ");
        List<String> valuesOfExpr = new ArrayList<>();
        Pattern pattern = Pattern.
                compile("(?i)^" + OPERAND_PATTERN + " " + OPERATOR_PATTERN + " " + OPERAND_PATTERN + "$");


        if (!cmd.matches("[^ ]* " + OPERATOR_PATTERN + " [^ ]*")) {
            valuesOfExpr.add("error > wrong expression");
            return valuesOfExpr;
        }


        if (!derrivedExpr[0].matches("(?i)^" + OPERAND_PATTERN + "$")){
            valuesOfExpr.add("error > " + derrivedExpr[0]);
            return valuesOfExpr;
        }

        if (!derrivedExpr[1].matches("(?i)^" + OPERATOR_PATTERN + "$")){
            valuesOfExpr.add("error > " + derrivedExpr[1]);
            return valuesOfExpr;
        }

        if (!derrivedExpr[2].matches("(?i)^" + OPERAND_PATTERN + "$")){
            valuesOfExpr.add("error > " + derrivedExpr[2]);
            return valuesOfExpr;
        }

        valuesOfExpr = patternCheck(pattern, cmd);
        return valuesOfExpr;
    }

    public List<String> checkUnaryOperation(String cmd) {
        List<String> valuesOfExpr = new ArrayList<>();
        String[] derrivedExpr = cmd.split(" ");
        Pattern pattern = Pattern.compile("(?i)^" + OPERATOR_PATTERN + " " + OPERAND_PATTERN + "$");

        if (!cmd.matches("[*+\\-/] .*")) {
            valuesOfExpr.add("error > wrong expression");
            return valuesOfExpr;
        }

        if (!derrivedExpr[1].matches("(?i)" + OPERAND_PATTERN)) {
            valuesOfExpr.add("error > " + derrivedExpr[1]);
            return valuesOfExpr;
        }

        valuesOfExpr = patternCheck(pattern,  cmd);
        return valuesOfExpr;

    }

    private List<String> patternCheck(Pattern pattern,  String cmd) {
        List<String> executedValues = new ArrayList<>();
        String delUnderline;
        Matcher m = pattern.matcher(cmd);

        if (m.find()) {
            for (int i=1; i<=m.groupCount(); i++) {
                delUnderline = m.group(i).toLowerCase().replace("_","");
                executedValues.add(delUnderline);
            }
        }
        return executedValues;
    }

    public String checkZeroAtTheEnd(String input) {
        if (input.matches("[+-]?\\d+.00")) {
            return input.substring(0, input.length() - 3);
        }
        if (input.matches("[+-]?\\d+.\\d+0")) {
            return input.substring(0, input.length() - 1);
        }

        return input;

    }
}