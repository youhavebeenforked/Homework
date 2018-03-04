package ru.sberbank.homework.bobrov.generics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionUtilsTest {
    private List src = new ArrayList<>();
    private List dst = new ArrayList<>();


    @Before
    public void prepareClassForTest() {
        for (int i = 0; i < 10; i++) {
            src.add(i);
        }
        Collections.shuffle(src);
        dst.clear();
    }

    @Test
    public void copyAllValuesFromListToList() {
        CollectionUtils.addAll(src, dst);
        Assert.assertEquals(src, dst);
    }

    @Test
    public void returnNewArrayList() {
        Assert.assertTrue(CollectionUtils.newArrayList() instanceof ArrayList);
    }


    @Test
    public void testSort() {
        System.out.println(CollectionUtils.range(src, 3, 7));
        System.out.println("a");

    }

}