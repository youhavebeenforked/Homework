package ru.sberbank.homework.kashin.task_02;

import org.junit.Before;
import org.junit.Test;
import ru.sberbank.homework.kashin.data.Person;
import ru.sberbank.homework.kashin.data.TestPerson;

import static org.junit.Assert.assertEquals;

public class ReflectionFieldCopierTest {
    private ReflectionFieldCopier fieldCopier;

    @Before
    public void before() {
        fieldCopier = new ReflectionFieldCopier();
    }

    @Test
    public void copy() {
        Person firstPerson = new TestPerson(10, "Ivan");
        Person secondPerson = new TestPerson();
        fieldCopier.copy(firstPerson, secondPerson);

        assertEquals(firstPerson, secondPerson);
    }
}