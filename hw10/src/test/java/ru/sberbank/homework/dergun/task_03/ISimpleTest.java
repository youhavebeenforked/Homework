package ru.sberbank.homework.dergun.task_03;

import ru.sberbank.homework.dergun.task_03.Annotation.Cache;
import ru.sberbank.homework.dergun.task_03.Annotation.IgnoreArgument;

import java.io.Serializable;
import java.util.List;

import static ru.sberbank.homework.dergun.task_03.Storage.CacheType.FILE;

public interface ISimpleTest extends Serializable{
    @Cache
    void increment();

    @Cache(cacheType = FILE, fileNamePrefix = "SimpleTestFile")
    void incrementFile();

    @Cache(cacheType = FILE)
    String setSomeArgument(@IgnoreArgument int count, String word, @IgnoreArgument int b);

    @Cache(cacheType = FILE, zip = true)
    void zip(@IgnoreArgument int count, String word, @IgnoreArgument int b);

    @Cache(maxSizeList = 5)
    List<Integer> returnListSize(int size);

    int getCount();
}
