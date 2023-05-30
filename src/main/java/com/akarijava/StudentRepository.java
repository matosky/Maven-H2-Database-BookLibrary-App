package com.akarijava;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;

public class StudentRepository {
    private SessionFactory sessionFactory;

    public StudentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void borrowBook(Long studentId, Long bookId) {
        StudentRepository studentRepository = new StudentRepository(sessionFactory);

        // Find the student by ID
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        // Check if the student already has a borrowed book
        if (student.isBorrowedStatus()) {
            System.out.println("You have already borrowed a book. Return the previous book before borrowing a new one.");
            return;
        }

        // Find the book by ID
        BookRepository bookRepository = new BookRepository(sessionFactory);
        Book book = bookRepository.findBookById(bookId);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        // Borrow the book
        student.setBorrowedStatus(true);
        student.setBorrowedBook(book);
        studentRepository.updateStudent(student);
        System.out.println("Book borrowed successfully.");
    }
    public Student findStudentById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Student.class, id);
        } finally {
            session.close();
        }
    }



    public void returnBook(Long studentId) {
        StudentRepository studentRepository = new StudentRepository(sessionFactory);

        // Find the student by ID
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        // Check if the student has a borrowed book
        if (!student.isBorrowedStatus()) {
            System.out.println("You don't have any borrowed books.");
            return;
        }

        // Return the book
        student.setBorrowedStatus(false);
        student.setBorrowedBook(null);
        studentRepository.updateStudent(student);
        System.out.println("Book returned successfully.");
    }
    public List<Student> getAllStudents() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List<Student> students = session.createQuery("FROM Student", Student.class).list();
            session.getTransaction().commit();
            return students;
        } finally {
            session.close();
        }
    }

    public void createStudent(Student student) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Long studentId = (Long) session.save(student);
            session.getTransaction().commit();
            student.setId(studentId);
            System.out.println("Student created successfully. Student ID: " + studentId);
        } finally {
            session.close();
        }
    }

    public void updateStudent(Student student) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
