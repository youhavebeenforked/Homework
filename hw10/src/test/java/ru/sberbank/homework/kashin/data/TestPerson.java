package ru.sberbank.homework.kashin.data;


import ru.sberbank.homework.kashin.task_03.annotations.Cache;

import java.util.Objects;

public class TestPerson implements Person {
    private int id;
    private String name;

    public TestPerson() {
    }

    public TestPerson(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Cache(typeStorage = true, name = "qwerty")
    @Override
    public int doItInFile(int i) {
        System.out.println("invoke method");
        return i * id * name.hashCode();
    }

    @Cache
    @Override
    public int doItInMemory(int i) {
        System.out.println("invoke method");
        return i * id * name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestPerson that = (TestPerson) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "TestPerson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
