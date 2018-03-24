package ru.sberbank.homework.andreev.task_01;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class AnnotationFinderAppTest {
    private AnnotationFinderApp annotationFinderApp;

    @Before
    public void setUp() throws Exception {
        annotationFinderApp = new AnnotationFinderApp("ru.sberbank.homework.common.entity");
    }

    @Test
    public void checkRightField() {
        annotationFinderApp.findAndPrint();
        Map<Class, List<Field>> fieldsWithAnnotation = annotationFinderApp.getFieldsWithAnnotation();
        List<String> actual = fieldsWithAnnotation.values().stream()
                .flatMap(e -> e.stream().map(Field::getName))
                .collect(Collectors.toList());
        assertEquals(Collections.singletonList("codename"), actual);
    }

    @Test
    public void checkRightMethods() {
        annotationFinderApp.findAndPrint();
        Map<Class, List<Method>> methodsWithAnnotation = annotationFinderApp.getMethodsWithAnnotation();
        List<String> actual = methodsWithAnnotation.values().stream()
                .flatMap(e -> e.stream().map(Method::getName))
                .collect(Collectors.toList());
        assertEquals(Collections.singletonList("fuelWithWater"), actual);
    }

    @Test
    public void checkRightClass() {
        annotationFinderApp.findAndPrint();
        Map<Class, List<Method>> methodsWithAnnotation = annotationFinderApp.getMethodsWithAnnotation();
        List<String> actual = methodsWithAnnotation.keySet().stream().map(Class::getSimpleName).collect(Collectors.toList());
        assertEquals(Collections.singletonList("TeslaModelHydro"), actual);
    }
}