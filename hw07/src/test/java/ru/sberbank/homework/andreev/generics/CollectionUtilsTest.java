package ru.sberbank.homework.andreev.generics;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CollectionUtilsTest {
    List<Integer> testList;

    @Before
    public void setUp() throws Exception {
        testList = new ArrayList<>(Arrays.asList(8, 1, 3, 5, 6, 4));
    }

    @Test
    public void addAll() {
        List<Integer> destinationList = new ArrayList<>(Arrays.asList(16, 25, 36, 49, 64, 81));
        List<Integer> rightResult = Arrays.asList(16, 25, 36, 49, 64, 81, 8, 1, 3, 5, 6, 4);
        CollectionUtils.addAll(testList, destinationList);
        assertTrue(rightResult.containsAll(destinationList));
    }

    @Test
    public void newArrayList() {
        List<Object> newList = CollectionUtils.newArrayList();
        newList.addAll(testList);
        assertTrue(newList.containsAll(testList));
    }

    @Test
    public void indexOf() {
        assertEquals(1, CollectionUtils.indexOf(testList, 1));
    }

    @Test
    public void limit() {
        assertEquals(Arrays.asList(8, 1, 3), CollectionUtils.limit(testList, 3));
    }

    @Test
    public void add() {
        CollectionUtils.add(testList, 5);
        assertEquals(Arrays.asList(8, 1, 3, 5, 6, 4, 5), testList);
    }

    @Test
    public void removeAll() {
        CollectionUtils.removeAll(testList, (Arrays.asList(1, 5, 4)));
        assertEquals(Arrays.asList(8, 3, 6), testList);
    }

    @Test
    public void containsAll() {
        assertTrue(CollectionUtils.containsAll(testList,testList.subList(0,5)));
    }

    @Test
    public void containsAny() {
       assertTrue(CollectionUtils.containsAny(testList, Arrays.asList(4,9,16, 25, 36, 49, 64, 81)));
    }

    @Test
    public void range() {
        List actual = CollectionUtils.range(Arrays.asList(8, 1, 3, 5, 6, 4), 3, 6);
        assertEquals(Arrays.asList(3, 5, 6, 4), actual);
    }

    @Test
    public void range1() {
        List actual = CollectionUtils.range(Arrays.asList(8, 1, 3, 5, 6, 4), 3, 6, Integer::compareTo);
        assertEquals(Arrays.asList(3, 5, 6, 4), actual);
    }
}