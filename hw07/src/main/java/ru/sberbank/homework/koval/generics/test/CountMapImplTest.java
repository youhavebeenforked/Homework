package ru.sberbank.homework.koval.generics.test;

import ru.sberbank.homework.koval.generics.CountMap;
import ru.sberbank.homework.koval.generics.CountMapImpl;

import java.util.*;

public class CountMapImplTest {
    private static final String ERROR_MSG = "Error: ";
    private static final String SUCCESS_MSG = " was passed successfully!";

    public static void run() {

        System.out.println("CountMapImplTest is started");

        addTest();
        getCountTest();
        removeTest();
        sizeTest();
        addAllTest();

        System.out.println("all tests were passed successfully!");
        System.out.println();
    }

    private static void addTest() {
        String testName = "addTest";
        String errorMessage = ERROR_MSG + testName;

        List<String> list = Arrays.asList("one", "two", new String("two"), "two", "three", "three");
        CountMap<String> map = new CountMapImpl<>();
        listToMap(list, map);

        compareCollections(map, list, errorMessage);
        System.out.println(testName + SUCCESS_MSG);
    }

    private static void getCountTest() {
        String testName = "getCountTest";
        String errorMessage = ERROR_MSG + testName;

        CountMap<String> map = new CountMapImpl<>();
        List<String> list = Arrays.asList("one", "two", new String("two"), "two", "three", "three");
        listToMap(list, map);

        Assert.assertEquals(ERROR_MSG + testName, map.getCount("two"), 3);
        System.out.println(testName + SUCCESS_MSG);
    }

    private static void removeTest() {
        String testName = "removeTest";
        String errorMessage = ERROR_MSG + testName;

        CountMap<String> map = new CountMapImpl<>();
        List<String> immutableList = Arrays.asList("one", "two", new String("two"), "two", "three", "three");
        List<String> list = new ArrayList<>(immutableList);
        listToMap(list, map);

        int numberInMap = map.remove("two");
        int numberInList = remove(list, "two");

        Assert.assertEquals(errorMessage, numberInMap, numberInList);
        compareCollections(map, list, errorMessage);
        System.out.println(testName + SUCCESS_MSG);
    }


    private static void sizeTest() {
        String testName = "sizeTest";
        String errorMessage = ERROR_MSG + testName;

        CountMap<String> map = new CountMapImpl<>();
        List<String> list = Arrays.asList("one", "two", new String("two"), "two", "three", "three");
        listToMap(list, map);

        Assert.assertEquals(errorMessage, map.size(), 3);
        System.out.println(testName + SUCCESS_MSG);
    }


    private static void addAllTest() {
        String testName = "addAllTest";
        String errorMessage = ERROR_MSG + testName;

        CountMap<Number> map = new CountMapImpl<>();
        List<Number> immutableList = Arrays.asList(1, 2, 4, 6, 2, 3, 4);
        List<Number> list = new ArrayList<>(immutableList);
        listToMap(list, map);

        CountMap<Long> sourceMap = new CountMapImpl<>();
        List<Long> sourceList = Arrays.asList(1L, 5L, 2L, 1L);
        listToMap(sourceList, sourceMap);

        list.addAll(sourceList);
        map.addAll(sourceMap);

        compareCollections(map, list, errorMessage);
        System.out.println(testName + SUCCESS_MSG);
    }

    private static <E> int remove(List<E> list, E item) {
        int numberInList = 0;
        Iterator<E> it = list.iterator();
        while (it.hasNext()) {
            E string = it.next();
            if (string.equals(item)) {
                ++numberInList;
                it.remove();
            }
        }
        return numberInList;
    }

    private static <E> void compareCollections(CountMap<? extends E> map, List<? extends E> list, String message) {
        List<E> listFromMap = new ArrayList<>();
        mapToList(map, listFromMap);
        Assert.assertEquals(message, listFromMap, list);
    }

    private static <E> void listToMap(List<E> list, CountMap<E> map) {
        for (E item : list) {
            map.add(item);
        }
    }

    private static <E> void mapToList(CountMap<? extends E> map, List<E> list) {
        Map<? extends E, Integer> simpleMap = map.toMap();
        for (Map.Entry<? extends E, Integer> entry : simpleMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); ++i) {
                list.add(entry.getKey());
            }
        }
    }
}
