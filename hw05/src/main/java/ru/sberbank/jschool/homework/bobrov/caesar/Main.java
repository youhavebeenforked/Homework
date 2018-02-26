package ru.sberbank.jschool.homework.bobrov.caesar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> cl = null;
        EncryptedClassLoader encr = new EncryptedClassLoader("ru.sberbank.jschool.homework.bobrov.caesar", 3);
        try {
            cl = encr.findClass("Test");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Object ob = cl.newInstance();
        Method md = cl.getMethod("show");
        md.invoke(ob);
    }
}
