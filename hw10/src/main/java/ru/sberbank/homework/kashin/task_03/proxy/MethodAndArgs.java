package ru.sberbank.homework.kashin.task_03.proxy;

import ru.sberbank.homework.kashin.task_03.annotations.Cache;

import java.lang.reflect.Method;

public final class MethodAndArgs {
    private final Method method;

    private final Object[] methodArgs;

    private final int methodHash;

    MethodAndArgs(final Method method, final Object[] args) {
        this.method = method;
        methodArgs = args;
        methodHash = calcHash();
    }

    @Override
    public boolean equals(final Object obj) {
        final MethodAndArgs other = (MethodAndArgs) obj;
        if (!method.equals(other.method)) {
            return false;
        }
        for (int i = 0; i < methodArgs.length; ++i) {
            Object o1 = methodArgs[i];
            Object o2 = other.methodArgs[i];
            if (!(o1 == null ? o2 == null : o1.equals(o2))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return methodHash;
    }

    private int calcHash() {
        int h = method.hashCode();
        for (final Object o : methodArgs) {
            h = h * 65599 + (o == null ? 0 : o.hashCode());
        }
        return h;
    }

    public String getFileName() {
        return method.getAnnotation(Cache.class).name().trim().isEmpty() ?
                method.getName() + methodHash :
                method.getAnnotation(Cache.class).name() + methodHash;
    }
}