package ru.sberbank.homework.kashin.task_03.annotations;

import ru.sberbank.homework.kashin.task_03.storage.TypeStorage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Данной аннотацией отмечается метод, для кэширования с помощью CacheProxy.
 *
 * @see ru.sberbank.homework.kashin.task_03.CacheProxy
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    /**
     * Параметр для указания где будет храниться кэш метода.
     * TypeStorage.FS - файловая система. TypeStorage.JVM - в памяти JVM. По умолчанию значение TypeStorage.JVM.
     *
     * @return значение для проверки где хранить кэш метода.
     */
    TypeStorage typeStorage() default TypeStorage.JVM;

    /**
     * Параметр для указания имени файла при хранении в файловой системе.
     * Если не указано или указано пустое имя, то имя файла будет сгенерированно
     * из имени метода и хэша аргументов.
     *
     * @return имя для файла, при хранении в файловой системе
     */
    String name() default "";

    /**
     * Параметр для указания номеров аргументов метода, для их игнорирования прри кэшировании.
     * Указывается в формате массива String, нумерация аргументов начинается с 0.
     * По умолчанию все аргументы учитываются.
     *
     * @return массив номеров аргументов, значения которых будут игнорироваться
     */
    String[] excludedFields() default "";
}
