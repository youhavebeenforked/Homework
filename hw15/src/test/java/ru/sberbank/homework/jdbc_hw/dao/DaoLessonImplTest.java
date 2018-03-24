package ru.sberbank.homework.jdbc_hw.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.sberbank.homework.jdbc_hw.entity.Lesson;
import ru.sberbank.homework.jdbc_hw.exception.SqlRuntimeException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static ru.sberbank.homework.jdbc_hw.util.HelperTest.*;

public class DaoLessonImplTest {
    private DaoLesson daoLesson = new DaoLessonImpl();

    @Before
    public void setUp() {
        fillingDatabase(daoLesson);
    }

    @After
    public void tearDown() {
        clearDB();
    }

    @Test
    public void addLessonSuccessful() {
        Lesson expected = new Lesson(5, "Physics", LocalDate.of(2018, 3, 20));
        daoLesson.addLesson(expected);
        Lesson actual = daoLesson.getLessonById(5);

        assertEquals(expected, actual);
    }

    @Test(expected = SqlRuntimeException.class)
    public void addLessonUnsuccessfulAddAnExistingLessonName() {
        daoLesson.addLesson(new Lesson(4, "Psychology", LocalDate.of(2018, 3, 23)));
    }

    @Test
    public void removeLessonById() {
        daoLesson.removeLessonById(4);
        Lesson deletedLesson = daoLesson.getLessonById(4);

        assertNull(deletedLesson);
    }

    @Test
    public void updateLesson() {
        Lesson expected = new Lesson(0, "Chemistry", LocalDate.of(2018, 3, 24));
        daoLesson.updateLesson(0, "Chemistry", LocalDate.of(2018, 3, 24));
        Lesson actual = daoLesson.getLessonById(0);

        assertEquals(expected, actual);
    }

    @Test
    public void getListLessons() {
        List<Lesson> expected = new ArrayList<>();
        expected.add(new Lesson(0, "English", LocalDate.of(2018, 3, 20)));
        expected.add(new Lesson(1, "Computer science", LocalDate.of(2018, 3, 21)));
        expected.add(new Lesson(2, "Algebra", LocalDate.of(2018, 3, 22)));
        expected.add(new Lesson(3, "History", LocalDate.of(2018, 3, 22)));
        expected.add(new Lesson(4, "Psychology", LocalDate.of(2018, 3, 23)));
        List<Lesson> actual = daoLesson.getListLessons();

        assertEquals(expected, actual);
    }

    @Test
    public void getLessonByIdSuccessful() {
        Lesson expected = new Lesson(3, "History", LocalDate.of(2018, 3, 22));
        Lesson actual = daoLesson.getLessonById(3);

        assertEquals(expected, actual);
    }

    @Test
    public void getLessonByIdUnsuccessful() {
        Lesson actual = daoLesson.getLessonById(5);

        assertNull(actual);
    }

    @Test
    public void getLessonByNameSuccessful() {
        Lesson expected = new Lesson(3, "History", LocalDate.of(2018, 3, 22));
        Lesson actual = daoLesson.getLessonByName("History");

        assertEquals(expected, actual);
    }

    @Test
    public void getLessonByNameUnsuccessful() {
        Lesson actual = daoLesson.getLessonByName("Geography");

        assertNull(actual);
    }
}