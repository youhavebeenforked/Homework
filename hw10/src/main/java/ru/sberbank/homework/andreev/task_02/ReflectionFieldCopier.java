package ru.sberbank.homework.andreev.task_02;

import org.apache.commons.lang3.ClassUtils;
import ru.sberbank.homework.common.BeanFieldCopier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReflectionFieldCopier implements BeanFieldCopier {
    private Object from;
    private Object to;

    @Override
    public void copy(Object from, Object to) {
        this.from = from;
        this.to = to;
        List<Method> toSetMethods = getMethodsByRegexp(to, "^set.+");
        List<Method> fromGetMethods = getMethodsByRegexp(from, "^get.+");

        toSetMethods.forEach(set -> invokeIfExist(fromGetMethods, set));
    }

    private void invokeIfExist(List<Method> getters, Method set) {
        Optional<Method> get = getters.stream().filter(e -> (e.getName().matches(set.getName().replaceFirst("^set", "get")))
                && (ClassUtils.isAssignable(e.getReturnType(), set.getParameterTypes()[0]))).findFirst();
        get.ifPresent(getter -> {
            try {
                set.invoke(to, getter.invoke(from));
            } catch (IllegalAccessException | InvocationTargetException e1) {
                e1.printStackTrace();
            }
        });
    }

    private List<Method> getMethodsByRegexp(Object obj, String regexp) {
        return Arrays.stream(obj.getClass().getMethods())
                .filter(e -> e.getName().matches(regexp)).collect(Collectors.toList());
    }
}
