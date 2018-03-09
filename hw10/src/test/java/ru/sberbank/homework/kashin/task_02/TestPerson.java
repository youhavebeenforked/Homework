package ru.sberbank.homework.kashin.task_02;

import ru.sberbank.homework.kashin.task_03.Cache;

import java.io.Serializable;
import java.util.Objects;

@Cache
public class TestPerson implements Serializable {
    private int id;
    private String name;
    private boolean active;

    public TestPerson(int id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public TestPerson() {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "TestPerson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestPerson testClass = (TestPerson) o;
        return id == testClass.id &&
                active == testClass.active &&
                Objects.equals(name, testClass.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, active);
    }
}
