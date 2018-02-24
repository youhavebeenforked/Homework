package ru.sberbank.homework.kashin.generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class CollectionUtilsTest {

    @Test
    public void addAll() {
        List<String> source = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc"));
        List<String> destination = new ArrayList<>(Arrays.asList("ddd", "eee"));
        CollectionUtils.addAll(source, destination);
        assertArrayEquals(new String[]{"ddd", "eee", "aaa", "bbb", "ccc"}, destination.toArray());
    }

    @Test
    public void newArrayList() {
        assertTrue(CollectionUtils.newArrayList() instanceof ArrayList);
    }

    @Test
    public void indexOf() {
        List<String> source = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc"));
        assertEquals(1, CollectionUtils.indexOf(source, "bbb"));
    }

    @Test
    public void limit() {
        List<String> source = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee", "fff"));
        assertArrayEquals(new String[]{"aaa", "bbb", "ccc", "ddd"}, CollectionUtils.limit(source, 4).toArray());
    }

    @Test
    public void add() {
        List<String> source = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc"));
        CollectionUtils.add(source, "ddd");
        assertArrayEquals(new String[]{"aaa", "bbb", "ccc", "ddd"}, source.toArray());
    }

    @Test
    public void removeAll() {
        List<String> removeFrom = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee", "fff"));
        List<String> c = new ArrayList<>(Arrays.asList("bbb", "ddd", "fff"));
        CollectionUtils.removeAll(removeFrom, c);
        assertArrayEquals(new String[]{"aaa", "ccc", "eee"}, removeFrom.toArray());
    }

    @Test
    public void containsAll() {
        List<String> c1 = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee", "fff"));
        List<String> c2 = new ArrayList<>(Arrays.asList("bbb", "ddd", "fff"));
        List<String> c3 = new ArrayList<>(Arrays.asList("bbb", "ddd", "hhh"));
        assertEquals(true, CollectionUtils.containsAll(c1, c2));
        assertEquals(false, CollectionUtils.containsAll(c1, c3));
    }

    @Test
    public void containsAny() {
        List<String> c1 = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee", "fff"));
        List<String> c2 = new ArrayList<>(Arrays.asList("rrr", "www", "fff"));
        List<String> c3 = new ArrayList<>(Arrays.asList("nnn", "mmm", "hhh"));
        assertEquals(true, CollectionUtils.containsAny(c1, c2));
        assertEquals(false, CollectionUtils.containsAny(c1, c3));
    }

    @Test
    public void range() {
        assertArrayEquals(new Integer[]{3, 4, 5, 6}, CollectionUtils.range(Arrays.asList(8, 1, 3, 5, 6, 4), 3, 6, Comparator.naturalOrder()).toArray());
    }
}