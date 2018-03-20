package ru.sberbank.homework.andreev.task_03.method_classes;

import ru.sberbank.homework.andreev.task_03.annotation.Cache;
import ru.sberbank.homework.andreev.task_03.annotation.CacheType;
import ru.sberbank.homework.andreev.task_03.annotation.Zip;

import java.math.BigDecimal;
import java.util.List;

public interface IMethodClass {

    @Cache(cacheType = CacheType.FILE, fileNamePrefix = "data", zip = Zip.FALSE, identityBy = {String.class}, listSize = 5)
    List<String> createStringList(int i, String str);

    Integer sum(Integer i1, Integer i2);

    BigDecimal discriminant(Double a, Double b, Double c);

    MethodClass mc();

    List<Integer> collectSubnumber(Integer i);
}
