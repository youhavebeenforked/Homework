package ru.sberbank.homework.kashin.task_01;

import javassist.NotFoundException;
import ru.sberbank.homework.common.annotation.ExperimentalFeature;
import ru.sberbank.homework.common.annotation.Prototype;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Finder {
    public Method getMethodWithAnnotationExperimentalFeature(Class clazz) throws NotFoundException {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getAnnotation(ExperimentalFeature.class) != null)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Метод с аннотацией ExperimentalFeature не найден"));
    }

    public Class getClassWithAnnotationPrototype(Class[] classes) throws NotFoundException {
        return Arrays.stream(classes)
                .filter(clazz -> clazz.getAnnotation(Prototype.class) != null)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Класс с аннотацией Prototype не найден"));
    }

    public Field getFieldWithAnnotationExperimentalFeature(Class clazz) throws NotFoundException {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.getAnnotation(ExperimentalFeature.class) != null)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Поле с аннотацией ExperimentalFeature не найдено"));
    }
}
