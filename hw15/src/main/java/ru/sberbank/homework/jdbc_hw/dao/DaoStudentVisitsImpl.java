package ru.sberbank.homework.jdbc_hw.dao;

import ru.sberbank.homework.jdbc_hw.entity.Lesson;
import ru.sberbank.homework.jdbc_hw.entity.Student;
import ru.sberbank.homework.jdbc_hw.entity.Visit;
import ru.sberbank.homework.jdbc_hw.exception.NoSuchLessonException;
import ru.sberbank.homework.jdbc_hw.exception.NoSuchStudentException;
import ru.sberbank.homework.jdbc_hw.exception.SqlRuntimeException;
import ru.sberbank.homework.jdbc_hw.util.Helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class DaoStudentVisitsImpl implements DaoStudentVisits {
    private DaoStudent daoStudent = new DaoStudentImpl();
    private DaoLesson daoLesson = new DaoLessonImpl();

    @Override
    public void addVisit(int studentId, int lessonId) {
        Student student = daoStudent.getStudentById(studentId);
        if (isNull(student)) {
            throw new NoSuchStudentException();
        }
        Lesson lesson = daoLesson.getLessonById(lessonId);
        if (isNull(lesson)) {
            throw new NoSuchLessonException();
        }

        try (Connection connection = Helper.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO STUDENT_VISITS (STUDENT_ID, LESSON_ID) VALUES (? , ?)");
            statement.setInt(1, studentId);
            statement.setInt(2, lessonId);
            statement.execute();
        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }

    @Override
    public void addVisit(int studentId, String lessonName) {
        Student student = daoStudent.getStudentById(studentId);
        if (isNull(student)) {
            throw new NoSuchStudentException();
        }
        Lesson lesson = daoLesson.getLessonByName(lessonName);
        if (isNull(lesson)) {
            throw new NoSuchLessonException();
        }

        try (Connection connection = Helper.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO STUDENT_VISITS (STUDENT_ID, LESSON_ID) VALUES (? , ?)");
            statement.setInt(1, studentId);
            statement.setInt(2, lesson.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }

    @Override
    public Visit getVisitById(int id) {
        try (Connection connection = Helper.getConnection()) {
            Visit visit = null;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM STUDENT_VISITS WHERE ID = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                visit = new Visit(
                        result.getInt(1),
                        result.getInt(2),
                        result.getInt(3));
            }
            return visit;
        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }

    @Override
    public List<Lesson> getAllLessonsOfStudentById(int studentId) {
        Student student = daoStudent.getStudentById(studentId);
        if (isNull(student)) {
            throw new NoSuchStudentException();
        }

        List<Lesson> listLessons = new ArrayList<>();
        try (Connection connection = Helper.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM LESSON INNER JOIN STUDENT_VISITS S ON LESSON.ID = S.LESSON_ID" +
                            " WHERE STUDENT_ID = ?");
            statement.setInt(1, studentId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Lesson lesson = new Lesson(
                        result.getInt(1),
                        result.getString(2),
                        result.getDate(3).toLocalDate());
                listLessons.add(lesson);
            }
            return listLessons;
        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }

    @Override
    public List<Student> getAllStudentsInLessonById(int lessonId) {
        Lesson lesson = daoLesson.getLessonById(lessonId);
        if (isNull(lesson)) {
            throw new NoSuchLessonException();
        }

        List<Student> listStudent = new ArrayList<>();
        try (Connection connection = Helper.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM STUDENT INNER JOIN STUDENT_VISITS S ON STUDENT.ID = S.STUDENT_ID" +
                            " WHERE LESSON_ID = ?");
            statement.setInt(1, lessonId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Student student = new Student(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3));
                listStudent.add(student);
            }
            return listStudent;
        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }

    @Override
    public void deleteAllLessonsOfStudentById(int studentId) {
        Student student = daoStudent.getStudentById(studentId);
        if (isNull(student)) {
            throw new NoSuchStudentException();
        }

        try (Connection connection = Helper.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM STUDENT_VISITS WHERE STUDENT_ID = ?");
            statement.setInt(1, studentId);
            statement.execute();
        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }

    @Override
    public void deleteAllStudentsOfLessonById(int lessonId) {
        Lesson lesson = daoLesson.getLessonById(lessonId);
        if (isNull(lesson)) {
            throw new NoSuchLessonException();
        }

        try (Connection connection = Helper.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM STUDENT_VISITS WHERE LESSON_ID = ?");
            statement.setInt(1, lessonId);
            statement.execute();
        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = Helper.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM STUDENT_VISITS WHERE ID = ?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }


}
