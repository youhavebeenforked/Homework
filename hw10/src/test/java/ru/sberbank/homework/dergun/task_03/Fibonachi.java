package ru.sberbank.homework.dergun.task_03;

public class Fibonachi implements IFibonachi {
    @Override
    public int fib(int n) {

        int a = 0;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            a += b;
            a ^= b;
            b ^= a;
            a ^= b;
        }
        return n == 0 ? 0 : b;
    }
}
