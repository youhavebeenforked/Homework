package ru.sberbank.homework.kashin.task_02;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReflectionFieldCopierTest {
    private ReflectionFieldCopier fieldCopier;

    @Before
    public void before() {
        fieldCopier = new ReflectionFieldCopier();
    }

    @Test
    public void copy() {
        TestPerson firstPerson = new TestPerson(10, "Ivan", true);
        TestPerson secondPerson = new TestPerson();
        fieldCopier.copy(firstPerson, secondPerson);

        assertEquals(firstPerson, secondPerson);
    }
}