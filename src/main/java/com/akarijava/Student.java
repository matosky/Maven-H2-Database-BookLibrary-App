package com.akarijava;
import javax.persistence.*;

    @Entity
    @Table(name = "Student")
    public class Student {
        @Id
        @Column(name = "student_id")
        private Long id;

        private String firstName;
        private String lastName;
        private boolean isBorrowedStatus;

        @ManyToOne
        @JoinColumn(name = "book_id")
        private Book borrowedBook;

        public Student(){}
    public Student(String firstName, String lastName, boolean isBorrowedStatus, Long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isBorrowedStatus = isBorrowedStatus;
        this.id = id;
    }

    // Getter methods

        public void setId(Long id){
            this.id = id;
        }

        public Long getId(){
            return  id;
        }
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }


    public boolean isBorrowedStatus() {
        return this.isBorrowedStatus;
    }

    // Setter methods
    public void setBorrowedStatus(boolean isBorrowedStatus) {
        this.isBorrowedStatus = isBorrowedStatus;
    }

    public void setBorrowedBook(Book book){
        this.borrowedBook = book;
    }


    // Utility method
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isBorrowedStatus=" + isBorrowedStatus +
                ", borrowedBook=" + borrowedBook +
                '}';
    }
}
