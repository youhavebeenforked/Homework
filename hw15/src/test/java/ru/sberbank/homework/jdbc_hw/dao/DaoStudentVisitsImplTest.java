package ru.sberbank.homework.jdbc_hw.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.sberbank.homework.jdbc_hw.entity.Lesson;
import ru.sberbank.homework.jdbc_hw.entity.Student;
import ru.sberbank.homework.jdbc_hw.entity.Visit;
import ru.sberbank.homework.jdbc_hw.exception.NoSuchLessonException;
import ru.sberbank.homework.jdbc_hw.exception.NoSuchStudentException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static ru.sberbank.homework.jdbc_hw.util.HelperTest.*;

public class DaoStudentVisitsImplTest {
    private DaoStudent daoStudent = new DaoStudentImpl();
    private DaoLesson daoLesson = new DaoLessonImpl();
    private DaoStudentVisits daoStudentVisits = new DaoStudentVisitsImpl();

    @Before
    public void setUp() {
        fillingDatabase(daoStudent);
        fillingDatabase(daoLesson);
        fillingDatabase(daoStudentVisits);
    }

    @After
    public void tearDown() {
        clearDB();
    }

    @Test
    public void addVisitByLessonIdSuccessful() {
        Visit expected = new Visit(5, 0, 0);
        daoStudentVisits.addVisit(0, 0);
        Visit actual = daoStudentVisits.getVisitById(5);

        assertEquals(expected, actual);
    }

    @Test(expected = NoSuchStudentException.class)
    public void addVisitByLessonIdNoSuchStudent() {
        daoStudentVisits.addVisit(7, 0);
    }

    @Test(expected = NoSuchLessonException.class)
    public void addVisitByLessonIdNoSuchLesson() {
        daoStudentVisits.addVisit(0, 7);
    }

    @Test
    public void addVisitByLessonNameSuccessful() {
        Visit expected = new Visit(5, 0, 0);
        daoStudentVisits.addVisit(0, "English");
        Visit actual = daoStudentVisits.getVisitById(5);

        assertEquals(expected, actual);
    }

    @Test(expected = NoSuchLessonException.class)
    public void addVisitByLessonNameNoSuchStudent() {
        daoStudentVisits.addVisit(0, "Chemistry");
    }

    @Test(expected = NoSuchLessonException.class)
    public void addVisitByLessonNameNoSuchLesson() {
        daoStudentVisits.addVisit(0, "Chemistry");
    }

    @Test
    public void getVisitById() {
        Visit expected = new Visit(0, 1, 1);
        Visit actual = daoStudentVisits.getVisitById(0);

        assertEquals(expected, actual);
    }

    @Test
    public void getAllLessonsOfStudentByIdSuccessful() {
        List<Lesson> expected = new ArrayList<>();
        expected.add(new Lesson(1, "Computer science", LocalDate.of(2018, 3, 21)));
        expected.add(new Lesson(2, "Algebra", LocalDate.of(2018, 3, 22)));

        List<Lesson> actual = daoStudentVisits.getAllLessonsOfStudentById(2);

        assertEquals(expected, actual);
    }

    @Test(expected = NoSuchStudentException.class)
    public void getAllLessonsOfStudentByIdUnsuccessful() {
        daoStudentVisits.getAllLessonsOfStudentById(7);
    }

    @Test
    public void getAllStudentsInLessonByIdSuccessful() {
        List<Student> expected = new ArrayList<>();
        expected.add(new Student(1, "Anton", "Sidorov"));
        expected.add(new Student(2, "Andrey", "Rogov"));

        List<Student> actual = daoStudentVisits.getAllStudentsInLessonById(1);

        assertEquals(expected, actual);
    }

    @Test(expected = NoSuchLessonException.class)
    public void getAllStudentsInLessonByIdUnsuccessful() {
        daoStudentVisits.getAllStudentsInLessonById(7);
    }

    @Test
    public void deleteAllLessonsOfStudentByIdSuccessful() {
        daoStudentVisits.deleteAllLessonsOfStudentById(2);
        List<Lesson> lessons = daoStudentVisits.getAllLessonsOfStudentById(2);

        assertTrue(lessons.size() == 0);
    }

    @Test(expected = NoSuchStudentException.class)
    public void deleteAllLessonsOfStudentByIdNoSuchStudent() {
        daoStudentVisits.deleteAllLessonsOfStudentById(7);
    }

    @Test
    public void deleteAllStudentsOfLessonByIdSuccessful() {
        daoStudentVisits.deleteAllStudentsOfLessonById(1);
        List<Student> students = daoStudentVisits.getAllStudentsInLessonById(1);

        assertTrue(students.size() == 0);
    }

    @Test(expected = NoSuchLessonException.class)
    public void deleteAllStudentsOfLessonByIdNoSuchStudent() {
        daoStudentVisits.deleteAllStudentsOfLessonById(7);
    }

    @Test
    public void deleteById() {
        daoStudentVisits.deleteById(0);
        Visit visit = daoStudentVisits.getVisitById(0);

        assertNull(visit);
    }
}