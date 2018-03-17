package ru.sberbank.homework.kashin.task_01;

import org.junit.Before;
import org.junit.Test;
import ru.sberbank.homework.common.entity.Car;
import ru.sberbank.homework.common.entity.TeslaModel3;
import ru.sberbank.homework.common.entity.TeslaModelHydro;

import java.io.IOException;

import static org.junit.Assert.*;

public class LoaderTest {
    private Loader loader;

    @Before
    public void before() {
        loader = new Loader("ru.sberbank.homework.common.entity");
    }

    @Test
    public void getClasses() throws IOException, ClassNotFoundException {
        Class[] expected = new Class[]{Car.class, TeslaModel3.class, TeslaModelHydro.class};
        Class[] actual = loader.getClasses();
        assertArrayEquals(expected, actual);
    }
}