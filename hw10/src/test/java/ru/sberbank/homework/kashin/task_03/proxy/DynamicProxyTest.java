package ru.sberbank.homework.kashin.task_03.proxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ru.sberbank.homework.kashin.datafortest.Person;
import ru.sberbank.homework.kashin.datafortest.DataForTestInFile;
import ru.sberbank.homework.kashin.datafortest.TestPerson;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class DynamicProxyTest {
    private DynamicProxy proxy = new DynamicProxy(DataForTestInFile.ROOT);

    @Test
    public void create() {
        String name = "Qwerty";
        Person person = new TestPerson(10, name);
        Person proxyPerson = proxy.create(person.getClass().getInterfaces(), person);

        int actual = proxyPerson.doItWithoutCache(20);
        int expected = 20 * 10 * name.hashCode();

        assertEquals(expected, actual);
    }
}