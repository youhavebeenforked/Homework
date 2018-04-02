package ru.sberbank.homework.jdbc_hw.dao;

import ru.sberbank.homework.jdbc_hw.entity.Lesson;
import ru.sberbank.homework.jdbc_hw.exception.SqlRuntimeException;
import ru.sberbank.homework.jdbc_hw.util.Helper;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DaoLessonImpl implements DaoLesson {

    @Override
    public void addLesson(Lesson lesson) {
        try (Connection connection = Helper.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO LESSON (NAME, DATE) VALUES (? , ?)");
            statement.setString(1, lesson.getName());
            statement.setDate(2, Date.valueOf(lesson.getDate()));
            statement.execute();
        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }

    @Override
    public void removeLessonById(int id) {
        try (Connection connection = Helper.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM LESSON WHERE ID = ?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }

    @Override
    public void updateLesson(int id, String newLessonName, LocalDate newLessonDate) {
        try (Connection connection = Helper.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE LESSON SET NAME = ?, DATE = ? WHERE ID = ?");
            statement.setInt(3, id);
            statement.setString(1, newLessonName);
            statement.setDate(2, Date.valueOf(newLessonDate));
            statement.execute();
        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }

    @Override
    public List<Lesson> getListLessons() {
        List<Lesson> listLessons = new ArrayList<>();
        try (Connection connection = Helper.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM LESSON");
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
    public Lesson getLessonById(int id) {
        try (Connection connection = Helper.getConnection()) {
            Lesson lesson = null;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM LESSON WHERE ID = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                lesson = new Lesson(
                        result.getInt(1),
                        result.getString(2),
                        result.getDate(3).toLocalDate());
            }
            return lesson;
        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }

    @Override
    public Lesson getLessonByName(String lessonName) {
        try (Connection connection = Helper.getConnection()) {
            Lesson lesson = null;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM LESSON WHERE NAME = ?");
            statement.setString(1, lessonName);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                lesson = new Lesson(
                        result.getInt(1),
                        result.getString(2),
                        result.getDate(3).toLocalDate());
            }
            return lesson;
        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }
}
