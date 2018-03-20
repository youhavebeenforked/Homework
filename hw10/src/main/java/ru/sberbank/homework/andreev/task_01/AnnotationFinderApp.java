package ru.sberbank.homework.andreev.task_01;

import ru.sberbank.homework.common.annotation.ExperimentalFeature;
import ru.sberbank.homework.common.annotation.Prototype;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;


public class AnnotationFinderApp {

    private List<Class> classesFromPackage;
    private Map<Class, List<Field>> fieldsWithAnnotation;
    private Map<Class, List<Method>> methodsWithAnnotation;


    public AnnotationFinderApp(String packagePath) {
        this.classesFromPackage = new ClassesFromPackageProvider(packagePath).getClassesFromPackage();
    }


    public void findAndPrint() {
        List<Class> classesWithAnnotation = getClassWithAnnotation(Prototype.class);
        fieldsWithAnnotation = classesWithAnnotation.stream()
                .collect(Collectors.toMap(Function.identity(), e -> getAnnotatedFields(e, ExperimentalFeature.class)));
        methodsWithAnnotation = classesWithAnnotation.stream()
                .collect(Collectors.toMap(Function.identity(), e -> getAnnotatedMethod(e, ExperimentalFeature.class)));
        classesWithAnnotation.forEach(this::print);

    }

    private List<Field> getAnnotatedFields(Class clazz, Class<? extends Annotation> annotationClass) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(e -> e.isAnnotationPresent(annotationClass))
                .collect(Collectors.toList());
    }

    private List<Method> getAnnotatedMethod(Class clazz, Class<? extends Annotation> annotationClass) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(e -> e.isAnnotationPresent(annotationClass))
                .collect(Collectors.toList());
    }


    private List<Class> getClassWithAnnotation(Class<? extends Annotation> annotation) {
        return classesFromPackage.stream().filter(e -> Objects.nonNull(
                e.getAnnotation(annotation))).collect(Collectors.toList());
    }

    private void print(Class prototypeClass) {
        System.out.println(prototypeClass.getSimpleName());
        Prototype annotation = (Prototype) prototypeClass.getAnnotation(Prototype.class);
        System.out.println("version is: " + annotation.version());
        System.out.println("Methods:");
        methodsWithAnnotation.get(prototypeClass).forEach(e -> System.out.println(e.getName()));
        System.out.println("Fields:");
        fieldsWithAnnotation.get(prototypeClass).forEach(e -> System.out.println(e.getName()));
    }

    public Map<Class, List<Field>> getFieldsWithAnnotation() {
        return fieldsWithAnnotation;
    }

    public Map<Class, List<Method>> getMethodsWithAnnotation() {
        return methodsWithAnnotation;
    }
}
