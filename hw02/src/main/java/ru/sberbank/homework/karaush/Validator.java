package ru.sberbank.homework.karaush;

public class Validator {

    public static void validate(String expr) {
        String[] lexemes = expr.split(" ");
        if (lexemes.length < 2 || lexemes.length > 3) {
            throw new IllegalArgumentException("error > wrong expression");
        }
    }

    public static double stringToDecimal(String number) {

        //regexp for int literals
        String hex = "^-?(0x|0X)[0-9a-fA-F_]*[0-9a-fA-F]+([lL])?$(?<!_)$";

        String octal = "^-?0[0-7_]*[0-7]+([lL])?$(?<!_)$";

        String binary = "^-?(0b|0B)[01_]*[01]+([lL])?$(?<!_)$";

        String decimal = "^-?[0-9]+[\\d_]*\\d*([lL])?$(?<!_)$";

        //regexp for float literal
        String floatRegexp = "^-?[0-9]+[\\d_]*\\d*(?<!_)\\.?([0-9]+[\\d_]*\\d*([fF])?$(?<!_)$)?";
        double answer;
        if (number.matches(hex)) {
            answer = Integer.valueOf(number.replaceAll("(_|0x|0X)", ""), 16);
        } else if (number.matches(decimal)) {
            answer = Integer.valueOf(number.replaceAll("_", ""), 10);
        } else if (number.matches(octal)) {
            answer = Integer.valueOf(number.replaceAll("_", ""), 8);
        } else if (number.matches(binary)) {
            answer = Integer.valueOf(number.replaceAll("(_|0b|0B)", ""), 2);
        } else if (number.matches(floatRegexp)) {
            answer = Double.valueOf(number.replaceAll("_", ""));
        } else {
            throw new IllegalArgumentException("error > " + number);
        }
        return answer;
    }
}
