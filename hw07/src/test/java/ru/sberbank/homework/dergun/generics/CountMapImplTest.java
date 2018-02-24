package ru.sberbank.homework.dergun.generics;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CountMapImplTest {
    public static void run() {

    }

    @Test
    public void add() throws Exception {
        CountMap<String> countMap = new CountMapImpl<>();
        String string = "qwerty";
        countMap.add(string);
        Assert.assertEquals("1 != 1", countMap.size(), 1);
        countMap.add("lalal");
        Assert.assertEquals("2 != 2", countMap.size(), 2);
        countMap.add("opop");
        countMap.add("opop");
        countMap.add("opop");
        Assert.assertEquals("3 != 3", countMap.size(), 3);
    }

    @Test
    public void getCount() throws Exception {
        CountMap<String> countMap = new CountMapImpl<>();
        String string = "qwerty";
        countMap.add(string);
        Assert.assertEquals("1", countMap.getCount(string), 1);
        countMap.add("lalal");
        Assert.assertEquals("2", countMap.getCount("lalal"), 1);
        countMap.add("opop");
        countMap.add("opop");
        countMap.add("opop");
        Assert.assertEquals("3", countMap.getCount("opop"), 3);
        countMap.add("qwerty");
        Assert.assertEquals("4", countMap.getCount(string), 2);
        Assert.assertEquals("5", countMap.getCount("qwerty"), 2);
    }

    @Test
    public void remove() throws Exception {
        CountMap<String> countMap = new CountMapImpl<>();
        String string = "qwerty";
        countMap.add(string);
        countMap.add("lalal");
        countMap.add("opop");
        countMap.add("opop");
        countMap.add("opop");
        Assert.assertEquals("1", countMap.remove(string), 1);
        Assert.assertEquals("2", countMap.remove(string), 0);
        Assert.assertEquals("3", countMap.remove("opop"), 3);
        Assert.assertEquals("4", countMap.remove("lalal"), 1);
    }

    @Test
    public void size() throws Exception {
        CountMap<String> countMap = new CountMapImpl<>();
        String string = "qwerty";
        Assert.assertEquals("1", countMap.size(), 0);
        countMap.add(string);
        Assert.assertEquals("1", countMap.size(), 1);
        countMap.add("lalal");
        countMap.add("opop");
        countMap.add("opop");
        countMap.add("opop");
        Assert.assertEquals("2", countMap.size(), 3);
        countMap.remove("opop");
        Assert.assertEquals("3", countMap.size(), 2);
        countMap.remove("lalal");
        Assert.assertEquals("4", countMap.size(), 1);
    }

    @Test
    public void addAll() throws Exception {
        CountMap<String> countMap1 = new CountMapImpl<>();
        CountMap<String> countMap2 = new CountMapImpl<>();
        countMap1.add("lalal");
        countMap1.add("opop");
        countMap1.add("opop");
        countMap1.add("qwerty");
        countMap1.add("a");
        countMap1.add("b");
        countMap1.add("c");
        countMap2.addAll(countMap1);
        Assert.assertEquals("1", countMap1.size(), countMap2.size());
        Assert.assertEquals("1", countMap1.getCount("qwerty"), countMap2.getCount("qwerty"));
        Assert.assertEquals("1", countMap1.getCount("opop"), countMap2.getCount("opop"));

    }

    @Test
    public void toMap() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        CountMap<String> countMap = new CountMapImpl<>();
        countMap.add("lalal");
        countMap.add("opop");
        countMap.add("opop");
        countMap.add("qwerty");
        countMap.add("a");
        countMap.add("b");
        countMap.add("c");
        countMap.toMap(map);
        Assert.assertEquals(countMap.toMap(), map);
    }

    @Test
    public void isEmpty() throws Exception {
        CountMap<String> countMap = new CountMapImpl<>();
        Assert.assertEquals(countMap.isEmpty(), true);
        countMap.add("lalal");
        Assert.assertEquals(countMap.isEmpty(), false);
        countMap.add("opop");
        countMap.add("opop");
        Assert.assertEquals(countMap.isEmpty(), false);
        countMap.remove("opop");
        countMap.remove("lalal");
        Assert.assertEquals(countMap.isEmpty(), true);
    }

}