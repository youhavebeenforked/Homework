package ru.sberbank.homework.jdbc_hw.dao;

import ru.sberbank.homework.jdbc_hw.entity.Lesson;

import java.time.LocalDate;
import java.util.List;

public interface DaoLesson {

    /**
     * Метод добавляет урок в БД
     *
     * @param lesson урок
     */
    void addLesson(Lesson lesson);

    /**
     * Метод удаляет урок по ид
     *
     * @param id ид урока
     */
    void removeLessonById(int id);

    /**
     * Метод позволяет изменить данные в БД об уроке с данным ид
     *
     * @param id ид урока который нужно изменить
     * @param newLessonName Новое имя урока
     * @param newLessonDate новая дата урока
     */
    void updateLesson(int id, String newLessonName, LocalDate newLessonDate);

    /**
     * Метод возвращает все уроки
     *
     * @return список уроков
     */
    List<Lesson> getListLessons();

    /**
     * Получить урок по данному ид
     *
     * @param id ид урока
     * @return урок
     */
    Lesson getLessonById(int id);

    /**
     * Получить урок по имени
     *
     * @param lessonName имя урока
     * @return урок
     */
    Lesson getLessonByName(String lessonName);
}
