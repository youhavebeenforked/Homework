package ru.sberbank.homework.kashin.task_03.proxy;

import org.junit.Test;
import ru.sberbank.homework.kashin.data.Person;
import ru.sberbank.homework.kashin.data.TestPerson;

public class DynamicProxyTest {

    @Test
    public void create() {
        Person person = new TestPerson(10, "Qwerty");

        DynamicProxy proxy = new DynamicProxy("storage");
        Person testPerson = proxy.create(person.getClass().getInterfaces(), person);

        System.out.println(testPerson.doItInMemory(10));
        System.out.println(testPerson.doItInMemory(10));
        System.out.println(testPerson.doItInMemory(20));
        System.out.println(testPerson.doItInMemory(10));
        System.out.println(testPerson.doItInMemory(20));
        System.out.println();
        System.out.println(testPerson.doItInFile(10));
        System.out.println(testPerson.doItInFile(10));
        System.out.println(testPerson.doItInFile(20));
        System.out.println(testPerson.doItInFile(10));
        System.out.println(testPerson.doItInFile(20));
    }
}