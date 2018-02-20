public class Assert {
    private static final double epsilon = 0.1e-4;  //Точность

    public static void equals(String message, double expected, double actual){
        if (Math.abs(expected-actual)>epsilon) throw new AssertionError(message);
    }

    public static void overflowOrDivisionByZero(String message, double a, double b, int operation){
        switch (operation) {
            case 1: if (Integer.MAX_VALUE-Math.abs((int)a)<(int)b) throw new AssertionError(message);
                break;
            case 2: if (Integer.MIN_VALUE+Math.abs((int)a)>(int)b) throw new AssertionError(message);
                break;
            case 3: if (a!=0 && Integer.MAX_VALUE/Math.abs((int)a)<Math.abs((int)b)) throw new AssertionError(message);
                break;
            case 5: if (Double.MAX_VALUE-Math.abs(a)<b) throw new AssertionError(message);
                break;
            case 6: if (Double.MAX_VALUE-Math.abs(a)<Math.abs(b)) throw new AssertionError(message);
                break;
            case 7: if (a!=0 && Double.MAX_VALUE/Math.abs(a)<Math.abs(b)) throw new AssertionError(message);
                break;
            default: if (b==0 && (operation==4 || operation==8) ) throw new AssertionError(message);
        }

    }

    public static void overflowIntVariable(String message, double d){
        if (d>Integer.MAX_VALUE || d<Integer.MIN_VALUE )
            throw new AssertionError(message);
    }

    public static void notValidArgument(String message, double d){
        if (Math.abs(d-(int)d)>0 ) throw new AssertionError(message);
    }
}