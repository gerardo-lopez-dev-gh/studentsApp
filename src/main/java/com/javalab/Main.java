package com.javalab;

import com.javalab.dao.StudentDAO;
import com.javalab.domain.Student;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("Welcome to the Student Management System!");
    boolean exit = false;
    Scanner scanner = new Scanner(System.in);
    StudentDAO studentDAO = new StudentDAO();
    while (!exit) {
      System.out.println("----------------------------------");
      showMenu();
      try {
        exit = executeOption(scanner, studentDAO);
      } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
      }
    }
  }

  private static void showMenu() {
    System.out.println(
"""
    1. List all students
    2. Find student by ID
    3. Add new student
    4. Update student
    5. Delete student
    6. Exit
""");
    System.out.println("Please select an option (1-6): ");
  }

  private static boolean executeOption(Scanner scanner, StudentDAO studentDAO) {
    var option = scanner.nextInt();
    boolean exit = false;
    switch (option) {
      case 1 -> {
        System.out.println("Listing all students:");
        studentDAO.getAll().forEach(System.out::println);
      }
      case 2 -> {
        System.out.println("Enter student ID to find:");
        int id = scanner.nextInt();
        Student student = new Student();
        student.setId(id);
        if (studentDAO.findById(student)) {
          System.out.println("Student found: " + student);
        } else {
          System.out.println("Student not found.");
        }
      }
      case 3 -> {
        System.out.println("Adding a new student:");
        Student newStudent = new Student();
        System.out.print("Name: ");
        newStudent.setName(scanner.next());
        System.out.print("Last Name: ");
        newStudent.setLastName(scanner.next());
        System.out.print("Phone Number: ");
        newStudent.setPhoneNumber(scanner.next());
        System.out.print("Email: ");
        newStudent.setEmail(scanner.next());
        if (studentDAO.addStudent(newStudent)) {
          System.out.println("New student added successfully.");
        } else {
          System.out.println("Failed to add new student.");
        }
      }
      case 4 -> {
        System.out.println("Updating a student:");
        Student updateStudent = new Student();
        System.out.print("Enter student ID to update: ");
        updateStudent.setId(scanner.nextInt());
        if (studentDAO.findById(updateStudent)) {
          System.out.print("New Name: ");
          updateStudent.setName(scanner.next());
          System.out.print("New Last Name: ");
          updateStudent.setLastName(scanner.next());
          System.out.print("New Phone Number: ");
          updateStudent.setPhoneNumber(scanner.next());
          System.out.print("New Email: ");
          updateStudent.setEmail(scanner.next());
          if (studentDAO.updateStudent(updateStudent)) {
            System.out.println("Student updated successfully.");
          } else {
            System.out.println("Failed to update student.");
          }
        } else {
          System.out.println("Student not found.");
        }
      }
      case 5 -> {
        System.out.println("Deleting a student:");
        Student deleteStudent = new Student();
        System.out.print("Enter student ID to delete: ");
        deleteStudent.setId(scanner.nextInt());
        if (studentDAO.deleteStudent(deleteStudent)) {
          System.out.println("Student deleted successfully.");
        } else {
          System.out.println("Failed to delete student.");
        }
      }
      case 6 -> {
        System.out.println("Exiting the application. Goodbye!");
        exit = true;
      }
    }
    return exit;
  }
}
