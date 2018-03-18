package ru.sberbank.homework.dergun.task_03.Annotation;

import ru.sberbank.homework.dergun.task_03.Storage.CacheType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    CacheType cacheType() default CacheType.MEMORY;

    String fileNamePrefix() default "";

    boolean zip() default false;

    String rootDirectory() default "";

    int maxSizeList() default -1;
}
