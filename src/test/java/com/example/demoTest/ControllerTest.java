package com.example.demoTest;
// JUnit 5: Marks a method as a test case.
import org.junit.jupiter.api.Test;

// JUnit 5: Defines the strategy for ordering test methods.
import org.junit.jupiter.api.MethodOrderer;

// JUnit 5: Annotation to specify how test methods should be ordered.
import org.junit.jupiter.api.TestMethodOrder;

// JUnit 5: Specifies the execution order of a test method when using ordered execution.
import org.junit.jupiter.api.Order;

// Spring: Enables automatic injection of Spring-managed beans.
import org.springframework.beans.factory.annotation.Autowired;

// Spring Boot Test: Loads the full application context for integration testing.
import org.springframework.boot.test.context.SpringBootTest;

// AssertJ: Provides fluent, readable assertions (e.g., assertThat(...).isEqualTo(...)).
import static org.assertj.core.api.Assertions.assertThat;

// Java standard library: Used to handle lists of objects.
import java.util.List;

/**
 * Integration test that verifies database operations using the real StudentRepository
 * and an in-memory H2 database (configured in src/test/resources/application.properties).
 * 
 * Tests are executed in a specific order to allow state sharing (for demonstration).
 */
@SpringBootTest          // Spring Boot annotation: starts full app context (including JPA, H2, repositories)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // JUnit 5: run tests in the order defined by @Order
class ControllerTest {

    @Autowired // Spring annotation: injects the StudentRepository bean from the application context
    private StudentRepository studentRepository;

    /**
     * Test 1: Saves a student and checks total count.
     */
    @Test      // JUnit 5: marks this method as a test
    @Order(1)  // JUnit 5: this test runs first
    void shouldSaveStudent() {
        Student student = new Student();
        student.setName("Mohamed");
        student.setAddress("Algeria");

        studentRepository.save(student); // Save to H2 database
        assertThat(studentRepository.count()).isEqualTo(1); // AssertJ: verify DB contains 1 record
    }

    /**
     * Test 2: Retrieves all students and validates content.
     */
    @Test      // JUnit 5: marks this method as a test
    @Order(2)  // JUnit 5: this test runs second
    void shouldFindAllStudents() {
        List<Student> students = studentRepository.findAll(); // Fetch all from H2
        for (Student s : students) {
            System.out.println("ID: " + s.getId() + ", Name: " + s.getName() + ", Address: " + s.getAddress());
        }
        assertThat(students).hasSize(1);                      // AssertJ: check list size
        assertThat(students.get(0).getName()).isEqualTo("Mohamed"); // AssertJ: check student name
    }
}