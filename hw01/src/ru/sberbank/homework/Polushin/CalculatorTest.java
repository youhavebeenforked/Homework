import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class CalculatorTest {
    static Calculator calc = new Calculator();
    private static DecimalFormat df4 = new DecimalFormat(".####"); //Формат вывода даблов
    public CalculatorTest(){
        //По умолчанию разделитель дробной части запятая, а нужна точка
        DecimalFormatSymbols dfs= df4.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df4.setDecimalFormatSymbols(dfs);

    }


    void twoPlusTwoTest() {
        int sum=calc.sum(2, 2);

        Assert.equals("2+2 must be equals 4, not a " + sum, sum, 4);
    }

    //Проверка умножения интов
    void multiplyIntTest(double a, double b) {
        int first= (int) a;
        int second= (int) b;

        notValidArgumentTest(a, b, 3); //являются ли введенные значения целыми числами
        overflowIntVariableTest(a, b); //Влазят ли эти цисла в инт
        overflowTest(first,second,3); //переполняется ли результат инт после умножения

        int res = calc.multiply(first, second);
        Assert.equals(first + "*" + second + " must be equals" + first * second + ", not a " + res, res, first * second);
    }

    //Проверка вычитания в даблах
    void subDoubleTest(double a, double b, double actual){
        overflowTest(a,b,6); //Проверка переполнения дабла

        double res =  calc.sub(a,b);
        Assert.equals(a + "-" + b + " must be equals " + df4.format(actual) + ", not a " + df4.format(res), res, actual);
    }

    //Деление в даблах
    void divisionDoubleTest(double a, double b, double actual){
        divisionByZeroTest(a, b, 8); // Проверка деления на ноль
        double res=calc.div(a,b);
        Assert.equals(a + "/" + b + " must be equals " + df4.format(actual) + ", not a " + df4.format(res), res, actual);
    }

    void overflowTest( double a, double b, int operation){
        Assert.overflowOrDivisionByZero("Overflow. Result of operation not in a type of operation. ", a, b, operation);
    }

    void divisionByZeroTest(double a, double b, int operation){
        Assert.overflowOrDivisionByZero("Division by zero", a, b,operation);
    }

    void overflowIntVariableTest (double a ,double b){
        Assert.overflowIntVariable("Overflow Integer type by variable "+a, a);
        Assert.overflowIntVariable("Overflow Integer type by variable "+b, b);
    }

    void notValidArgumentTest (double a , double b, int operation){
        if (operation<=4){
            Assert.notValidArgument("Variable "+a+" not an integer", a);
            Assert.notValidArgument("Variable "+b+" not an integer", b);
        }

    }
    public void run(){
        //тест 2+2
        try{
            twoPlusTwoTest();
            System.out.println("Test 2+2 passed");
        }
        catch (AssertionError error){
            System.out.println(error.getMessage());
        }

        // не является интом
        try {
            multiplyIntTest(3, 5);
            System.out.println("Test for int variables passed");
            multiplyIntTest(3, 5.5);
            System.out.println("Test for int variables passed");
        }
        catch (AssertionError error){
            System.out.println(error.getMessage());
        }

        //не влазит в инт
        try {
            multiplyIntTest(30L, 5000000);
            System.out.println("Test for overflow int variables passed");
            multiplyIntTest(3000000000L, 5000000);
            System.out.println("Test for overflow int variables passed");
        }
        catch (AssertionError error){
            System.out.println(error.getMessage());
        }

        //переполнение инта после вычисления
        try {
            multiplyIntTest(3000, 5000);
            System.out.println("Test for overflow result of int passed");
            multiplyIntTest(3000000, 5000000);
            System.out.println("Test for overflow result of int passed");
        }
        catch (AssertionError error){
            System.out.println(error.getMessage());
        }

        //деление на ноль
        try {
            divisionDoubleTest(4, 1.5, 2.667);
            System.out.println("Test for division doubles passed");
            divisionDoubleTest(4, 0, 4);
            System.out.println("Test for division doubles passed");
        }
        catch (AssertionError error) {
            System.out.println(error.getMessage());
        }

        //вычитание в double
        try {
            subDoubleTest(4.6,2.6,2);
            System.out.println("Test for sub doubles passed");
            subDoubleTest(4.6455,2.6454,2.0001);
            System.out.println("Test for sub doubles passed");
        }
        catch (AssertionError error) {
            System.out.println(error.getMessage());
        }
    }
}