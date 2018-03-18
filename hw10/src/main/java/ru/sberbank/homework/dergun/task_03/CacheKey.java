package ru.sberbank.homework.dergun.task_03;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

public class CacheKey implements Serializable{
    private final Method method;
    private final Object[] args;

    public CacheKey(Method method, Object[] args) {
        this.method = method;
        this.args = args;
    }

    public String getFileName() {
        return method.getName() + hashCode();
    }

    @Override
    public int hashCode() {
        return method.hashCode() ^ Arrays.hashCode(method.getParameterTypes()) ^ Arrays.hashCode(args);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CacheKey)) {
            return false;
        }
        CacheKey newKey = (CacheKey) obj;
        if (!method.equals(newKey.method)) {
            return false;
        }
        if (newKey.args == null) {
            if (args == null) {
                return true;
            }
            return false;
        }
        for (int index = 0; index < args.length; index++) {
            if (!args[index].equals(newKey.args[index])) {
                return false;
            }
        }
        return Arrays.equals(method.getParameterTypes(), newKey.method.getParameterTypes());
    }

}
