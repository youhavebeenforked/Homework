package ru.sberbank.homework.jdbc_hw.dao;

import ru.sberbank.homework.jdbc_hw.entity.Lesson;
import ru.sberbank.homework.jdbc_hw.entity.Student;
import ru.sberbank.homework.jdbc_hw.entity.Visit;

import java.util.List;

public interface DaoStudentVisits {

    /**
     * Добавляет студента с данным ид на урок с данным ид.
     *
     * @param studentId ид студента
     * @param lessonId ид урока
     */
    void addVisit(int studentId, int lessonId);

    /**
     * Добавляет студента на урок с данным именем
     *
     * @param studentId ид студента
     * @param lessonName имя урока
     */
    void addVisit(int studentId, String lessonName);

    /**
     * Возвращает объект Visit
     *
     * @param id ид визита
     * @return Объект содержащий ид студента и ид урока
     */
    Visit getVisitById(int id);

    /**
     * Метод позволяет получить все уроки на которые записан студент с данным ид
     *
     * @param studentId ид студента
     * @return список уроков
     */
    List<Lesson> getAllLessonsOfStudentById(int studentId);

    /**
     * Метод позволяет получить всех студентов записанных на урок с данным ид
     *
     * @param lessonId ид урока
     * @return список студентов
     */
    List<Student> getAllStudentsInLessonById(int lessonId);

    /**
     * Метод позволяет удалить студента с данным ид все уроки
     *
     * @param studentId ид студента
     */
    void deleteAllLessonsOfStudentById(int studentId);

    /**
     * Метод позволяет удалить у урока с данным ид всех студентов
     *
     * @param lessonId
     */
    void deleteAllStudentsOfLessonById(int lessonId);

    /**
     * Метод удаляет визит с данным ид
     *
     * @param id ид визита
     */
    void deleteById(int id);
}
