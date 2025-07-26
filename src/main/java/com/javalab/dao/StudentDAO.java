package com.javalab.dao;

import com.javalab.domain.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import static com.javalab.connection.ConnectionDB.getConnection;

// Data Access Object (DAO) for Student entity
public class StudentDAO {
  public List<Student> getAll() {
    List<Student> students = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection connection = getConnection();
    String sql = "SELECT * FROM students order by id";
    try {
      ps = connection.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setLastName(rs.getString("last_name"));
        student.setPhoneNumber(rs.getString("phone"));
        student.setEmail(rs.getString("email"));
        students.add(student);
      }
    } catch (Exception e) {
      System.out.println("Error retrieving students: " + e.getMessage());
    } finally {
      try {
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (connection != null) connection.close();
      } catch (Exception e) {
        System.out.println("Error closing resources: " + e.getMessage());
      }
    }
    return students;
  }

  public boolean findById(Student student) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection connection = getConnection();
    String sql = "SELECT * FROM students WHERE id = ?";
    try {
      ps = connection.prepareStatement(sql);
      ps.setInt(1, student.getId());
      rs = ps.executeQuery();
      if (rs.next()) {
        student.setName(rs.getString("name"));
        student.setLastName(rs.getString("last_name"));
        student.setPhoneNumber(rs.getString("phone"));
        student.setEmail(rs.getString("email"));
        return true;
      } else {
        System.out.println("Student not found with id: " + student.getId());
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error preparing statement: " + e.getMessage());
      return false;
    } finally {
      try {
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (connection != null) connection.close();
      } catch (Exception e) {
        System.out.println("Error closing resources: " + e.getMessage());
      }
    }
  }

  public boolean addStudent(Student student) {
    PreparedStatement ps = null;
    Connection connection = getConnection();
    String sql = "INSERT INTO students (name, last_name, phone, email) VALUES (?, ?, ?, ?)";
    try {
      ps = connection.prepareStatement(sql);
      ps.setString(1, student.getName());
      ps.setString(2, student.getLastName());
      ps.setString(3, student.getPhoneNumber());
      ps.setString(4, student.getEmail());
      ps.execute();
      return true;
    } catch (Exception e) {
      System.out.println("Error adding student: " + e.getMessage());
      return false;
    } finally {
      try {
        if (ps != null) ps.close();
        if (connection != null) connection.close();
      } catch (Exception e) {
        System.out.println("Error closing resources: " + e.getMessage());
      }
    }
  }
}
