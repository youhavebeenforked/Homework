package ru.sberbank.homework.jdbc_hw.entity;

import java.util.Objects;

public class Visit {
    private int id;
    private int studentId;
    private int lessonId;

    public Visit(int studentId, int lessonId) {
        this.studentId = studentId;
        this.lessonId = lessonId;
    }

    public Visit(int id, int studentId, int lessonId) {
        this.id = id;
        this.studentId = studentId;
        this.lessonId = lessonId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return id == visit.id &&
                studentId == visit.studentId &&
                lessonId == visit.lessonId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, studentId, lessonId);
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", lessonId=" + lessonId +
                '}';
    }
}
