package ru.sberbank.homework.andreev.generics;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class CountableMapTest {
    CountableMap<String> testCountableMap;

    @Before
    public void setUp() throws Exception {
        testCountableMap = new CountableMap<>();
        IntStream.rangeClosed(1,3).forEach(e->testCountableMap.add("foo"));
        IntStream.rangeClosed(1,4).forEach(e->testCountableMap.add("bar"));
        IntStream.rangeClosed(1,10).forEach(e->testCountableMap.add("buz"));
    }

    @Test
    public void add() {
        testCountableMap.add("buz");
        assertEquals(11,testCountableMap.getCount("buz"));
    }


    @Test
    public void remove() {
        testCountableMap.remove("foo");
        assertEquals(0, testCountableMap.getCount("foo"));

    }

    @Test
    public void size() {
        testCountableMap.add("yadayadayada");
        assertEquals(4, testCountableMap.size());
    }

    @Test
    public void addAll() {
        CountableMap<String> expected = new CountableMap<>();
        IntStream.rangeClosed(1,1).forEach(e->expected.add("foldr"));
        IntStream.rangeClosed(1,12).forEach(e->expected.add("42"));
        IntStream.rangeClosed(1,3).forEach(e->expected.add("foo"));
        IntStream.rangeClosed(1,4).forEach(e->expected.add("bar"));
        IntStream.rangeClosed(1,10).forEach(e->expected.add("buz"));

        CountableMap<String> toAdd = new CountableMap<>();
        IntStream.rangeClosed(1,1).forEach(e->testCountableMap.add("foldr"));
        IntStream.rangeClosed(1,12).forEach(e->testCountableMap.add("42"));

        testCountableMap.addAll(toAdd);

        assertEquals(expected.toMap(), testCountableMap.toMap());
    }

}