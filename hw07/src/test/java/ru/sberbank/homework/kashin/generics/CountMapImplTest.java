package ru.sberbank.homework.kashin.generics;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CountMapImplTest {

    @Test
    public void add() {
        CountMap<String> map = new CountMapImpl<>();
        map.add("aaa");
        map.add("aaa");
        assertEquals(2, map.getCount("aaa"));
    }

    @Test
    public void getCount() {
        CountMap<String> map = new CountMapImpl<>();
        map.add("sdf");
        map.add("fff");
        map.add("sdf");
        map.add("jfd");
        map.add("sdf");
        assertEquals(3, map.getCount("sdf"));
    }

    @Test
    public void remove() {
        CountMap<String> map = new CountMapImpl<>();
        map.add("sdf");
        map.add("fff");
        map.add("sdf");
        map.add("jfd");
        map.add("sdf");
        assertEquals(3, map.remove("sdf"));
    }

    @Test
    public void size() {
        CountMap<String> map = new CountMapImpl<>();
        map.add("sdf");
        map.add("fff");
        map.add("sdf");
        map.add("jfd");
        map.add("sdf");
        assertEquals(3, map.size());
    }

    @Test
    public void addAll() {
        CountMap<String> map = new CountMapImpl<>();
        map.add("sdf");
        map.add("fff");

        CountMap<String> source = new CountMapImpl<>();
        source.add("aaa");
        source.add("aaa");

        map.addAll(source);

        assertEquals(2, map.getCount("aaa"));
    }

    @Test
    public void toMap() {
        CountMap<String> source = new CountMapImpl<>();
        source.add("aaa");
        source.add("aaa");

        assertArrayEquals(new String[]{"aaa"}, source.toMap().keySet().toArray());
    }

    @Test
    public void toMap1() {
        CountMap<String> source = new CountMapImpl<>();
        source.add("aaa");
        source.add("aaa");

        Map<String, Integer> map = new HashMap<>();
        source.toMap(map);

        assertArrayEquals(new String[]{"aaa"}, map.keySet().toArray());
    }
}