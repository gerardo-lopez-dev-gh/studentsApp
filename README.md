# Student Management System

This is a simple CRUD (Create, Read, Update, Delete) application for managing student records. The application is built using Java and MySQL.

## Project Structure

```
studentsApp/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── javalab/
│                   ├── Main.java              # Main application entry point
│                   ├── connection/            
│                   │   └── ConnectionDB.java   # Database connection manager
│                   ├── dao/
│                   │   └── StudentDAO.java     # Data Access Object for Student
│                   └── domain/
│                       └── Student.java        # Student model class
├── docker-compose.yml                          # Docker setup for MySQL database
└── pom.xml                                     # Maven project configuration
```

## Features

- List all students
- Find student by ID
- Add new student
- Update student information
- Delete student records
- Console-based user interface

## Technologies Used

- Java 21
- MySQL 8.0
- JDBC for database connectivity
- Maven for dependency management
- Docker for database containerization

## Getting Started

### Prerequisites

- JDK 21 or higher
- Docker and Docker Compose
- Maven

### Database Setup

1. Start the MySQL database using Docker:

```bash
docker-compose up -d
```

This will create a MySQL instance with:
- Database: `student_db`
- User: `myuser`
- Password: `mypassword`
- Port: `3306`

2. The database will be initialized with necessary tables on first run.

### Running the Application

To build and run the application:

```bash
# Compile the project
mvn compile

# Run the main class
mvn exec:java -Dexec.mainClass="com.javalab.Main"
```

## Database Structure

The application uses a single table:

### Table: students

| Column      | Type         | Description              |
|-------------|--------------|--------------------------|
| id          | INT          | Primary Key, Auto Inc    |
| name        | VARCHAR(50)  | First name of student    |
| last_name   | VARCHAR(50)  | Last name of student     |
| phone       | VARCHAR(15)  | Contact phone number     |
| email       | VARCHAR(100) | Email address            |

## Development

This project follows a layered architecture:
- **Domain Layer**: Contains the Student class representing the student entity
- **DAO Layer**: Handles database operations for the Student entity
- **Connection Layer**: Manages database connections
- **Presentation Layer**: Console interface for user interaction

## License

This project is open-source and available under the MIT License.

## Author

gerardo-lopez-dev
