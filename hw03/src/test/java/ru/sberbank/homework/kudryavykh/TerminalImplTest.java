package ru.sberbank.homework.kudryavykh;

import org.junit.Test;

import static org.junit.Assert.*;

public class TerminalImplTest {

    class A {
        int test;

        public A(int test) {
            this.test = test;
        }

        public A() {
            this.test = 0;
        }
    }

    @Test
    public void pin() throws InvalidCardNumber {
        A a = new A();
        TerminalServer t = new TerminalServer(123L);
    }

    @Test
    public void getMoney() {
    }

    @Test
    public void setMoney() {
    }

    @Test
    public void checkAccount() {
    }
}