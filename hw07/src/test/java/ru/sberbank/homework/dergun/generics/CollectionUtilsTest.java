package ru.sberbank.homework.dergun.generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface A {

}

interface B extends A {

}

class C implements B {

}

class D extends C {

}

public class CollectionUtilsTest {
    @Test
    public void addAll() throws Exception {

    }

    @Test
    public void newArrayList() throws Exception {
        List<Integer> numbers = CollectionUtils.newArrayList();
        numbers.add(3);
    }

    @Test
    public void indexOf() throws Exception {
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(10);
        l.add(123);
        System.out.println(CollectionUtils.indexOf(l, 1));
        System.out.println(CollectionUtils.indexOf(l, 10.0));
        System.out.println(CollectionUtils.indexOf(l, 123));

    }

    @Test
    public void limit() throws Exception {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            l.add(i);
        }
        l = CollectionUtils.limit(l, 5);
        for (Object i : l) {
            System.out.println(i);
        }
    }

    @Test
    public void add() throws Exception {
        List<D> l = new ArrayList<>();
        l.add(new D());
        l.add(new D());
        CollectionUtils.add(l, new D());
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(10);
        list.add(123);
        for (Object i : list) {
            System.out.println(i);
        }
    }

    @Test
    public void removeAll() throws Exception {
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            A.add(i);
            B.add(i - 3);
        }
        CollectionUtils.removeAll(A, B);
        for (Object i : A) {
            System.out.println(i);
        }

        for (Object i : B) {
            System.out.println(i);
        }

    }

    @Test
    public void containsAll() throws Exception {
        List<C> a = new ArrayList<>();
        List<C> c = new ArrayList<>();
        List<D> d = new ArrayList<>();
        C elementC = new C();
        C elementC2 = new C();
        D elementD = new D();
        CollectionUtils.add(c, elementC);
        CollectionUtils.add(c, elementC2);
        CollectionUtils.add(c, elementD);
        CollectionUtils.add(d, elementD);
        System.out.println(CollectionUtils.containsAll(c, d));
        System.out.println(CollectionUtils.containsAll(d, c));
        System.out.println(CollectionUtils.containsAll(a, d));
        System.out.println(CollectionUtils.containsAll(d, a));
    }

    @Test
    public void containsAny() throws Exception {
        List<C> c = new ArrayList<>();
        List<D> d = new ArrayList<>();
        C elementC = new C();
        C elementC2 = new C();
        D elementD = new D();
        D elementD2 = new D();
        CollectionUtils.add(c, elementC);
        System.out.println(CollectionUtils.containsAny(c, d));
        CollectionUtils.add(c, elementC2);
        CollectionUtils.add(c, elementD2);
        System.out.println(CollectionUtils.containsAny(c, d));
        CollectionUtils.add(d, elementD);
        System.out.println(CollectionUtils.containsAny(c, d));
        System.out.println(CollectionUtils.containsAny(d, c));
    }

    @Test
    public void range() throws Exception {
        System.out.println((CollectionUtils.range(Arrays.asList(8,1,3,5,6, 4), 3, 6)));
    }

    @Test
    public void range1() throws Exception {
        System.out.println((CollectionUtils.range(Arrays.asList(8, 1, 3, 5, 6, 4), 3, 6, Integer::compareTo)));
    }

    @Test
    public void simple() {
        List<C> c = new ArrayList<>();
        c.add(new C());
        List<D> d = new ArrayList<>();
        d.add(new D());
    }
}