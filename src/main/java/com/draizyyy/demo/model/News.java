package com.draizyyy.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String authorName;

    public Long getId() {
        return id;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getDate() {
        return date;
    }

    public String getNewsText() {
        return newsText;
    }

    public String date;
    public String newsText;
    public News(String authorName, String date, String newsText) {
        this.authorName = authorName;
        this.date = date;
        this.newsText = newsText;
    }
}
