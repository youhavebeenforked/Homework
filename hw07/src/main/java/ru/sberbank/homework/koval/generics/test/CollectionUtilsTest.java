package ru.sberbank.homework.koval.generics.test;

import ru.sberbank.homework.koval.generics.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CollectionUtilsTest {
    private static final String ERROR_MSG = "Error: ";
    private static final String SUCCESS_MSG = " was passed successfully!";

    public static void run() {

        System.out.println("CollectionUtilsTest is started");
        addAllTest();
        indexOfTest();
        limitTest();
        addTest();
        removeAllTest();
        containsAllTest();
        containsAnyTest();
        rangeTest();
        rangeWithComparatorTest();

        System.out.println("all tests were passed successfully!");
        System.out.println();
    }

    private static void addAllTest() {
        String testName = "addAllTest";
        String errorMessage = ERROR_MSG + testName;

        List<Number> immutableList = Arrays.asList(1, 2, 4, 6, 2, 3, 4);

        List<Number> list = new ArrayList<Number>(immutableList);
        List<Number> checkList = new ArrayList<Number>(immutableList);

        List<Long> sourceList = Arrays.asList(1L, 5L, 2L, 1L);

        CollectionUtils.<Number>addAll(sourceList, list);
        checkList.addAll(sourceList);

        Assert.assertEquals(errorMessage, list, checkList);
        System.out.println(testName + SUCCESS_MSG);
    }

    private static void indexOfTest() {
        String testName = "indexOfTest";
        String errorMessage = ERROR_MSG + testName;

        List<Number> immutableList = Arrays.asList(1, 2, 4, 6, 2, 3, 4);
        List<Number> list = new ArrayList<Number>(immutableList);
        list.add(23L);

        int idx = CollectionUtils.<Number>indexOf(list, 23L);

        Assert.assertEquals(errorMessage, idx, 7);
        System.out.println(testName + SUCCESS_MSG);
    }

    private static void limitTest() {
        String testName = "limitTest";
        String errorMessage = ERROR_MSG + testName;

        List<Number> immutableList = Arrays.asList(1, 2, 4, 6, 2, 3, 4);
        List<Number> list = new ArrayList<>(immutableList);

        List<Number> resultList = CollectionUtils.<Number>limit(list, 4);
        List<Number> checkList = Arrays.asList(1, 2, 4, 6);

        Assert.assertEquals(errorMessage, resultList, checkList);
        System.out.println(testName + SUCCESS_MSG);
    }

    private static void addTest() {
        String testName = "addTest";
        String errorMessage = ERROR_MSG + testName;

        List<Integer> immutableList = Arrays.asList(1, 2, 4, 6, 2, 3, 4);
        List<Integer> list = new ArrayList<>(immutableList);

        CollectionUtils.add(list, 8);
        List<Integer> checkList = Arrays.asList(1, 2, 4, 6, 2, 3, 4, 8);

        Assert.assertEquals(errorMessage, list, checkList);
        System.out.println(testName + SUCCESS_MSG);
    }

    private static void removeAllTest() {
        String testName = "removeAllTest";
        String errorMessage = ERROR_MSG + testName;

        List<Integer> immutableList = Arrays.asList(1, 2, 4, 6, 2, 3, 4);
        List<Integer> list = new ArrayList<>(immutableList);
        List<Integer> toRemove = Arrays.asList(2, 6, 3, 8);

        CollectionUtils.removeAll(list, toRemove);

        List<Integer> checkList = Arrays.asList(1, 4, 4);

        Assert.assertEquals(errorMessage, list, checkList);
        System.out.println(testName + SUCCESS_MSG);
    }

    private static void containsAllTest() {
        String testName = "containsAllTest";
        String errorMessage = ERROR_MSG + testName;

        List<Number> immutableList = Arrays.asList(1, 2, 4, 6, 2, 3, 4);
        List<Number> list = new ArrayList<>(immutableList);
        List<Integer> subList = Arrays.asList(2, 6, 3);

        boolean isContains = CollectionUtils.<Number>containsAll(list, subList);

        Assert.assertEquals(errorMessage, isContains, true);
        System.out.println(testName + SUCCESS_MSG);
    }

    private static void containsAnyTest() {
        String testName = "containsAnyTest";
        String errorMessage = ERROR_MSG + testName;

        List<Number> immutableList = Arrays.asList(1, 2, 4, 6, 2, 3, 4);
        List<Number> list = new ArrayList<>(immutableList);
        List<Integer> subList = Arrays.asList(5, 6, 10);

        boolean isContains = CollectionUtils.<Number>containsAny(list, subList);

        Assert.assertEquals(errorMessage, isContains, true);
        System.out.println(testName + SUCCESS_MSG);
    }

    private static void rangeTest() {
        String testName = "rangeTest(naturalOrder)";
        String errorMessage = ERROR_MSG + testName;

        List<String> list = Arrays.asList("ABCD", "ABCDE", "BCDE", "BA", "AB", "CD", "C");

        List<String> resultList = CollectionUtils.<String>range(list, "A", "B");
        List<String> checkList = Arrays.asList("ABCD", "ABCDE", "AB");

        Assert.assertEquals(errorMessage, resultList, checkList);
        System.out.println(testName + SUCCESS_MSG);
    }

    private static void rangeWithComparatorTest() {
        String testName = "rangeTest";
        String errorMessage = ERROR_MSG + testName;

        List<String> list = Arrays.asList("ABCD", "ABCDE", "BCD", "BA", "AB", "CD", "C");

        List<String> resultList = CollectionUtils.<String>range(list, "AA", "BBB", (Comparator.comparing(String::length)));
        List<String> checkList = Arrays.asList("BCD", "BA", "AB", "CD");

        Assert.assertEquals(errorMessage, resultList, checkList);
        System.out.println(testName + SUCCESS_MSG);
    }
}
