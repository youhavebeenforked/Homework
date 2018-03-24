package ru.sberbank.homework.andreev.task_03.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    public CacheType cacheType() default CacheType.EMPTY;
    public String fileNamePrefix() default "";
    public Zip zip() default Zip.EMPTY;
    public Class[] identityBy() default {};
    public int listSize() default 0;
}
