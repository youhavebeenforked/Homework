package ru.sberbank.homework.kashin.task_01;

import javassist.NotFoundException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static ru.sberbank.homework.kashin.task_01.Helper.getClasses;

public class AnnotationFinderApp {
    private static Finder finder = new Finder();

    public static void main(String[] args) {
        try {
            Class[] classes = getClasses("ru.sberbank.homework.common.entity");

            Class clazz = finder.getClassWithAnnotationPrototype(classes);
            System.out.println("Имя класса с аннотацией Prototype: " + clazz.getSimpleName());

            Method method = finder.getMethodWithAnnotationExperimentalFeature(clazz);
            System.out.println("Имя метода с аннотацией ExperimentalFeature: " + method.getName());

            Field field = finder.getFieldWithAnnotationExperimentalFeature(clazz);
            System.out.println("Имя поля с аннотацией ExperimentalFeature: " + field.getName());

        } catch (ClassNotFoundException | IOException | NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
