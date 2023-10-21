package com.example.homecredit.enity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.io.Serializable;


@Entity
@Table(name = "Quote")
public class QuoteEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TEXT")
    @Size(max = 3000)
    private String text;

    @Column(name = "AUTHOR")
    @Size(max = 200)
    private String author;

    public QuoteEntity() {
    }

    public QuoteEntity(String text, String author) {
        this.text = text;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "QuoteEntity{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}