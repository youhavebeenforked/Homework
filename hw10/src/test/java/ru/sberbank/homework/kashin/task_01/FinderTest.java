package ru.sberbank.homework.kashin.task_01;

import javassist.NotFoundException;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.sberbank.homework.common.entity.Car;
import ru.sberbank.homework.common.entity.TeslaModel3;
import ru.sberbank.homework.common.entity.TeslaModelHydro;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class FinderTest {
    private static Finder finder;
    private static Class tesla;
    private static Class[] classes = new Class[]{Car.class, TeslaModel3.class, TeslaModelHydro.class};

    @BeforeClass
    public static void beforeClass() {
        finder = new Finder();
        tesla = TeslaModelHydro.class;
    }

    @Test
    public void getMethodWithAnnotationExperimentalFeature() throws NotFoundException, NoSuchMethodException {
        Method expected = TeslaModelHydro.class.getMethod("fuelWithWater", int.class);
        Method actual = finder.getMethodWithAnnotationExperimentalFeature(tesla);
        assertEquals(expected, actual);
    }

    @Test
    public void getClassWithAnnotationPrototype() throws NotFoundException {
        Class expected = TeslaModelHydro.class;
        Class actual = finder.getClassWithAnnotationPrototype(classes);
        assertEquals(expected, actual);
    }

    @Test
    public void getFieldWithAnnotationExperimentalFeature() throws NoSuchFieldException, NotFoundException {
        Field expected = TeslaModelHydro.class.getDeclaredField("codename");
        Field actual = finder.getFieldWithAnnotationExperimentalFeature(tesla);
        assertEquals(expected, actual);
    }
}