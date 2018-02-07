/**
 * Created by Иван on 07.02.2018.
 */
public class SimpleCalculator {

    public int sum(int one, int two) {
        return one + two;
    }

    public double sum(double one, double two) {
        return one + two;
    }

    public int sub(int one, int two) {
        return one - two;
    }

    public double sub(double one, double two) {
        return one - two;
    }

    public int mult(int one, int two) {
        return one * two;
    }

    public double mult(double one, double two) {
        return one * two;
    }

    public int div(int one, int two) {
        if (two == 0) {
            throw new AssertExpectedError("Exception zero");
        }
        return one / two;
    }

    public double div(double one, double two) {
        if (two == 0.0) {
            throw new AssertExpectedError("Exception zero");
        }
        return one / two;
    }
}
