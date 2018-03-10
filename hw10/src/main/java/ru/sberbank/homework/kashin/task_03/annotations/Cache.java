package ru.sberbank.homework.kashin.task_03.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Данной аннотацией отмечается метод, для кэширования с помощью CacheProxy.
 * @see ru.sberbank.homework.kashin.task_03.CacheProxy
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    /**
     * Параметр для указания где будет храниться кэш метода.
     * true - файловая система. false - в памяти JVM. По умолчанию значение false.
     * @return значение для проверки где хранить кэш метода.
     */
    boolean typeStorage() default false;

    /**
     * Параметр для указания имени файла при хранении в файловой системе.
     * Если не указано или указано пустое имя, то имя файла будет сгенерированно
     * из имени метода и хэша аргументов.
     * @return имя для файла, при хранении в файловой системе
     */
    String name() default "";
}
