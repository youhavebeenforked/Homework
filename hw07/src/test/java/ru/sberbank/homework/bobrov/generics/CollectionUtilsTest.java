package ru.sberbank.homework.bobrov.generics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionUtilsTest {
    private List<Integer> first = new ArrayList<>();
    private List<Integer> second = new ArrayList<>();

    @Before
    public void prepareClassForTest() {
        for (int i = 0; i < 10; i++) {
            first.add(i);
        }
        Collections.shuffle(first);
        second.clear();
    }

    @Test
    public void copyAllValuesFromListToList() {
        CollectionUtils.addAll(first, second);
        Assert.assertEquals(first, second);
    }

    @Test
    public void returnNewArrayList() {
        Assert.assertTrue(CollectionUtils.newArrayList() instanceof ArrayList);
    }

    @Test
    public void returnIndexByValue() {
        int value = 3;
        Assert.assertTrue(CollectionUtils.indexOf(Arrays.asList(1, 2, 3), value) == 2);
    }

    @Test
    public void returnSpecifiedSizeOfArray() {
        int size = 4;
        first = CollectionUtils.limit(first, size);
        Assert.assertTrue(first.size() == 4);
    }

    @Test
    public void addSpecifiedValueToList() {
        second.addAll(first);
        second.add(11);
        CollectionUtils.add(first, 11);

    }

    @Test
    public void removeDuplicateValue() {
        List<Integer> tmp = Arrays.asList(0, 1, 2, 3, 4);
        second = Arrays.asList(5, 6, 7, 8, 9);
        CollectionUtils.removeAll(first, tmp);
        Collections.sort(first);
        Assert.assertEquals(first, second);
    }

    @Test
    public void checkFirstListContainsAllValueFromSecondList() {
        second = Arrays.asList(1, 3, 5, 7, 9);
        Assert.assertTrue(CollectionUtils.containsAll(first, second));
    }

    @Test
    public void checkFirstListContainsAtLeastOneFromSecondList() {
        second = Arrays.asList(11, 3);
        Assert.assertTrue(CollectionUtils.containsAny(first, second));
    }

    @Test
    public void checkLimitSortWithComparable() {
        second = Arrays.asList(3, 4, 5, 6);
        Assert.assertEquals(CollectionUtils.range(first, 3, 6), second);
    }

    @Test
    public void checkLimitSortWithComparator() {
        second = Arrays.asList(3, 4, 5, 6);
        Comparator<Integer> comparator = (o1, o2) -> (Integer) o1 - (Integer) o2;
        Assert.assertEquals(CollectionUtils.range(first, 3, 6, comparator), second);
    }

}