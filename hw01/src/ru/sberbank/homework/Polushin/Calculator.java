import java.text.DecimalFormatSymbols;
import java.util.Scanner;
import java.text.DecimalFormat;


public class Calculator {
    private static DecimalFormat df3 = new DecimalFormat("0.000");

    public Calculator(){
        //По умолчанию разделитель дробной части запятая, а нужна точка
        DecimalFormatSymbols dfs= df3.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df3.setDecimalFormatSymbols(dfs);
    }

    public  int sum(int a, int b){
        return a+b;
    }

    public int sub(int a, int b){
        return a-b;
    }

    public int div(int a, int b){
        return a/b;
    }

    public int multiply(int a, int b){
        return a*b;
    }

    public double sum(double a, double b){
        String res = df3.format(a+b);
        return Double.valueOf((res));
    }

    public double sub(double a, double b){
        String res = df3.format(a-b);
        return Double.valueOf((res));
    }

    public double div(double a, double b){
        String res = df3.format(a / b);
        return Double.valueOf(res);
    }

    public double multiply(double a, double b){
        String res = df3.format(a*b);
        return Double.valueOf((res));
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        for(;;){
            System.out.println("This is SimpleCalculator. For exit input q, for calculate - smth other");
            if (scanner.next().equals("q")==true) return;

        double a,b;
        System.out.println("First, input two variable:");
        a= scanner.nextInt();
        b= scanner.nextInt();

        System.out.println("Second ,you need input number 1-8 to choose the operation:\n"+
                "1: sum of integers\n"+
                "2: substraction of integers\n"+
                "3: devision integers\n"+
                "4: multiply integers\n"+
                "5: sum of double\n"+
                "6: substraction of double\n"+
                "7: devision doubles\n"+
                "8: multiply doubles");

        switch (scanner.nextInt()) {
            case 1: System.out.println(a+"+"+b+"="+sum((int)a,(int)b));
                break;
            case 2: System.out.println(a+"-"+b+"="+sub((int)a,(int)b));
                break;
            case 3: System.out.println(a+"/"+b+"="+ div((int) a, (int) b));
                break;
            case 4: System.out.println(a+"*"+b+"="+multiply((int)a,(int)b));
                break;
            case 5: System.out.println(a+"+"+b+"="+sum(a,b));
                break;
            case 6: System.out.println(a+"-"+b+"="+sub(a,b));
                break;
            case 7: System.out.println(a+"/"+b+"="+ div(a, b));
                break;
            case 8: System.out.println(a+"*"+b+"="+multiply(a,b));
                break;
            default:
                System.out.println("Something went wrong, try again");
        }

        }
    }


}