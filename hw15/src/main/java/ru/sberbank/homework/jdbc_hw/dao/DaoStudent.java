package ru.sberbank.homework.jdbc_hw.dao;

import ru.sberbank.homework.jdbc_hw.entity.Student;

import java.util.List;

public interface DaoStudent {

    /**
     * Метод добавляет студента в БД
     *
     * @param student студент
     */
    void addStudent(Student student);

    /**
     * Удалить студента по ид
     *
     * @param id ид студента
     */
    void removeStudentById(int id);

    /**
     * Метод обновляет данные о студенте
     *
     * @param id ид студента которого нужно обновить
     * @param newFirstName новое имя студента
     * @param newLastName новая фамилия студента
     */
    void updateStudent(int id, String newFirstName, String newLastName);

    /**
     * Метод возврещает всех студентов
     *
     * @return список студентов
     */
    List<Student> getListStudents();

    /**
     * Возвращает студента по ид
     *
     * @param id ид студента
     * @return студент
     */
    Student getStudentById(int id);
}
