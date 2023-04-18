package com.draizyyy.myreportcard;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class News {
    @PrimaryKey
    int id;
    @ColumnInfo(name = "Author name")
    public String authorName;
    @ColumnInfo(name = "Date")
    public String date;
    @ColumnInfo(name = "News text")
    public String newsText;
    public News(String authorName, String date, String newsText) {
        this.authorName = authorName;
        this.date = date;
        this.newsText = newsText;
    }
}
