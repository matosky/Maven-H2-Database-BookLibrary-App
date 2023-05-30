package com.akarijava;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class App {
    public static void main(String[] args) {
        try (Session session = DatabaseConfig.getSession()) {
            Transaction transaction = session.beginTransaction();

            // Create a student
            StudentRepository studentRepository = new StudentRepository((SessionFactory) session);
            Student student = new Student("John", "Doe", false,  1L);
            studentRepository.createStudent(student);

            // Create a book
            BookRepository bookRepository = new BookRepository((SessionFactory) session);
            Book book = new Book("Sample Book", "Sample Author", 1L);
            bookRepository.createBook(book);

            // Borrow a book
            Long studentId = student.getId();
            Long bookId = book.getId();
            studentRepository.borrowBook(studentId, bookId);

            // Return the borrowed book
            studentRepository.returnBook(studentId);

            transaction.commit();

            List<Student> studentList = studentRepository.getAllStudents();
            for(Student std:studentList){
                System.out.println(std);
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
