package ru.sberbank.homework.jdbc_hw.dao;

import ru.sberbank.homework.jdbc_hw.entity.Student;
import ru.sberbank.homework.jdbc_hw.exception.SqlRuntimeException;
import ru.sberbank.homework.jdbc_hw.util.Helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoStudentImpl implements DaoStudent {

    @Override
    public void addStudent(Student student) {
        try (Connection connection = Helper.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO STUDENT (FIRST_NAME, LAST_NAME) VALUES (? , ?)");
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.execute();
        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }

    @Override
    public void removeStudentById(int id) {
        try (Connection connection = Helper.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM STUDENT WHERE ID = ?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }

    @Override
    public void updateStudent(int id, String newFirstName, String newLastName) {
        try (Connection connection = Helper.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE STUDENT SET FIRST_NAME = ?, LAST_NAME = ? WHERE ID = ?");
            statement.setInt(3, id);
            statement.setString(1, newFirstName);
            statement.setString(2, newLastName);
            statement.execute();
        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }

    @Override
    public List<Student> getListStudents() {
        List<Student> listStudent = new ArrayList<>();
        try (Connection connection = Helper.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM STUDENT");
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
    public Student getStudentById(int id) {
        try (Connection connection = Helper.getConnection()) {
            Student student = null;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM STUDENT WHERE ID = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                student = new Student(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3));
            }
            return student;
        } catch (SQLException e) {
            throw new SqlRuntimeException(e.getCause());
        }
    }
}
