package ru.sberbank.homework.jdbc_hw.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.sberbank.homework.jdbc_hw.entity.Student;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static ru.sberbank.homework.jdbc_hw.util.HelperTest.*;

public class DaoStudentImplTest {
    private DaoStudent daoStudent = new DaoStudentImpl();

    @Before
    public void setUp() {
        fillingDatabase(daoStudent);
    }

    @After
    public void tearDown() {
        clearDB();
    }

    @Test
    public void addStudent() {
        Student expected = new Student(5, "Alex", "Sokolov");
        daoStudent.addStudent(expected);
        Student actual = daoStudent.getStudentById(5);
        assertEquals(expected, actual);
    }

    @Test
    public void removeStudentById() {
        daoStudent.removeStudentById(4);
        Student deletedStudent = daoStudent.getStudentById(4);

        assertNull(deletedStudent);
    }

    @Test
    public void updateStudent() {
        Student expected = new Student(0, "Denis", "Smirnov");
        daoStudent.updateStudent(0, "Denis", "Smirnov");
        Student actual = daoStudent.getStudentById(0);

        assertEquals(expected, actual);
    }

    @Test
    public void getListStudent() {
        List<Student> expected = new ArrayList<>();
        expected.add(new Student(0, "Ivan", "Petrov"));
        expected.add(new Student(1, "Anton", "Sidorov"));
        expected.add(new Student(2, "Andrey", "Rogov"));
        expected.add(new Student(3, "Fedor", "Volkov"));
        expected.add(new Student(4, "Roman", "Vetrov"));

        List<Student> actual = daoStudent.getListStudents();

        assertEquals(expected, actual);
    }

    @Test
    public void getStudentByIdSuccessful() {
        Student expected = new Student(3, "Fedor", "Volkov");
        Student actual = daoStudent.getStudentById(3);

        assertEquals(expected, actual);
    }

    @Test
    public void getStudentByIdUnsuccessful() {
        Student actual = daoStudent.getStudentById(5);

        assertNull(actual);
    }
}