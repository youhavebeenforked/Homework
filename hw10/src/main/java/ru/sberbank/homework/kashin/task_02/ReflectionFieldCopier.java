package ru.sberbank.homework.kashin.task_02;

import ru.sberbank.homework.common.BeanFieldCopier;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class ReflectionFieldCopier implements BeanFieldCopier {

    @Override
    public void copy(Object from, Object to) {
        Map<String, Object> getters = getGetters(from);
        Map<String, Method> setters = getSetters(to);
        if (nonNull(getters)) {
            getters.entrySet().forEach(entry -> invokeSetter(to, setters, entry));
        }
    }

    private Map<String, Method> getSetters(Object object) {
        return Arrays.stream(object.getClass().getMethods())
                .filter(method -> (method.getName().startsWith("set")))
                .collect(Collectors.toMap(Method::getName, method -> method));
    }

    private Map<String, Object> getGetters(Object object) {
        try {
            return Arrays.stream(Introspector.getBeanInfo(object.getClass(), Object.class).getPropertyDescriptors())
                    .filter(property -> nonNull(property.getReadMethod()))
                    .collect(Collectors.toMap(
                            PropertyDescriptor::getName,
                            pd -> {
                                try {
                                    return pd.getReadMethod().invoke(object);
                                } catch (Exception e) {
                                    return null;
                                }
                            }));
        } catch (IntrospectionException e) {
            return null;
        }
    }

    private String getterToSetterName(String method) {
        return  "set" +  method.substring(0, 1).toUpperCase() + method.substring(1);
    }

    private void invokeSetter(Object to, Map<String, Method> setters, Map.Entry<String, Object> getter) {
        String setterName = getterToSetterName(getter.getKey());
        Method methodSetter = setters.get(setterName);
        if (methodSetter != null) {
            try {
                methodSetter.invoke(to ,getter.getValue());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
