package com.javalab;

import com.javalab.dao.StudentDAO;
import com.javalab.domain.Student;

public class Main {
  public static void main(String[] args) {
    Student student = new Student(1, "John", "Doe", "1234567890", "email@email.com");
    System.out.println(student);
    StudentDAO studentDAO = new StudentDAO();
    System.out.println("All students:");
    studentDAO.getAll().forEach(System.out::println);
    System.out.println("Find student by ID:");
    if (studentDAO.findById(student)) {
      System.out.println("Student found: " + student);
    } else {
      System.out.println("Student not found with ID: " + student.getId());
    }
    System.out.println("Adding new student:");
    Student newStudent = new Student("Jane", "Doe", "0987654321", "rumba@gmail.com");
    if (studentDAO.addStudent(newStudent)) {
      System.out.println("New student added: " + newStudent);
    } else {
      System.out.println("Failed to add new student.");
    }
    System.out.println("All students:");
    studentDAO.getAll().forEach(System.out::println);
  }
}
