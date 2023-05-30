package com.akarijava;

import javax.persistence.*;
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "book_id")
    private Long id;
    private String title;
    private String author;



    public Book(){}
    public Book(String title, String author, Long id){
        this.title = title;
        this.author = author;
        this.id = id;
    }


    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    // Utility method
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

}
