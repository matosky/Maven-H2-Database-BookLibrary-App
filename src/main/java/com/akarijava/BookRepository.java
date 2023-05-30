package com.akarijava;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BookRepository {
    private SessionFactory sessionFactory;

    public BookRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createBook(Book book) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Long bookId = (Long) session.save(book);
            session.getTransaction().commit();
            book.setId(bookId);
            System.out.println("Book created successfully. Book ID: " + bookId);
        } finally {
            session.close();
        }
    }

    public Book findBookById(Long bookId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Book book = session.get(Book.class, bookId);
            session.getTransaction().commit();
            return book;
        } finally {
            session.close();
        }
    }
}
