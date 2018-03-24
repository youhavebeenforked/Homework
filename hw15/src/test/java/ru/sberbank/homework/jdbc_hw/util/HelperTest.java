package ru.sberbank.homework.jdbc_hw.util;

import ru.sberbank.homework.jdbc_hw.dao.DaoLesson;
import ru.sberbank.homework.jdbc_hw.dao.DaoStudent;
import ru.sberbank.homework.jdbc_hw.dao.DaoStudentVisits;
import ru.sberbank.homework.jdbc_hw.entity.Lesson;
import ru.sberbank.homework.jdbc_hw.entity.Student;
import ru.sberbank.homework.jdbc_hw.exception.SqlRuntimeException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static ru.sberbank.homework.jdbc_hw.util.Helper.getConnection;

public class HelperTest {

    public static void clearDB() {
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.execute("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK");
            connection.commit();

        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }

    public static void fillingDatabase(DaoStudent daoStudent) {
        daoStudent.addStudent(new Student(0, "Ivan", "Petrov"));
        daoStudent.addStudent(new Student(1, "Anton", "Sidorov"));
        daoStudent.addStudent(new Student(2, "Andrey", "Rogov"));
        daoStudent.addStudent(new Student(3, "Fedor", "Volkov"));
        daoStudent.addStudent(new Student(4, "Roman", "Vetrov"));
    }

    public static void fillingDatabase(DaoLesson daoLesson) {
        daoLesson.addLesson(new Lesson(0, "English", LocalDate.of(2018, 3, 20)));
        daoLesson.addLesson(new Lesson(1, "Computer science", LocalDate.of(2018, 3, 21)));
        daoLesson.addLesson(new Lesson(2, "Algebra", LocalDate.of(2018, 3, 22)));
        daoLesson.addLesson(new Lesson(3, "History", LocalDate.of(2018, 3, 22)));
        daoLesson.addLesson(new Lesson(4, "Psychology", LocalDate.of(2018, 3, 23)));
    }

    public static void fillingDatabase(DaoStudentVisits daoStudentVisits) {
        daoStudentVisits.addVisit(1, 1);
        daoStudentVisits.addVisit(2, 1);
        daoStudentVisits.addVisit(2, 2);
        daoStudentVisits.addVisit(3, 3);
        daoStudentVisits.addVisit(3, 4);
    }
}