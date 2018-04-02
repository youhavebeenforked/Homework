package ru.sberbank.homework.jdbc_hw.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Lesson {
    private int id;
    private String name;
    private LocalDate date;

    public Lesson() {
    }

    public Lesson(String name, LocalDate date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Lesson(int id, String name, LocalDate date) {
        this.id = id;
        this.name = name;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return id == lesson.id &&
                Objects.equals(name, lesson.name) &&
                Objects.equals(date, lesson.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, date);
    }
}
