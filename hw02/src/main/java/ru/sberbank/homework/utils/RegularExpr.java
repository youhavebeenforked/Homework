package ru.sberbank.homework.utils;

public enum RegularExpr {
    OPERATOR("[\\s][+,\\-,*,\\/,^][\\s]") {

    },
    SHORT_OPERATOR("^[+,\\-,*,\\/,^][\\s]") {

    },
    INTEGER_TYPE("[+,-]?((0B)[0,1]+([_]*[0,1]+)*|[0][_]*[0-7]+([_]*[0-7]+)*" +
            "|(0X)[0-9,A-F]+([_]*[0-9,A-F]+)*|[1-9]+([_]*[0-9]+)*|[0]+)[L]?") {

    },
    DOUBLE_TYPE("[+,-]?((([1-9]([_]*[0-9]+)*)|([0]*[.][0-9]([_]*[0-9]+)*)){1,2}([E][+,-]?[0-9]([_]*[0-9]+)*)?)[F,D]?") {

    },
    DOUBLE_HEX("[+,-]?((0X)(([0-9,A-F]([_]*[0-9,A-F]+)*)|([.][0-9,A-F]([_]*[0-9,A-F]+)*)){1,2}[P][+,-]?[0-9]+)[F,D]?") {

    },
    NUMBER("(" + INTEGER_TYPE.getRegExp() + "|" + DOUBLE_TYPE.getRegExp() + "|" + DOUBLE_HEX.getRegExp() + ")") {

    };

    private String regExp;

    RegularExpr(String regExp) {
        this.regExp = regExp;
    }

    public String getRegExp() {
        return regExp;
    }

}
