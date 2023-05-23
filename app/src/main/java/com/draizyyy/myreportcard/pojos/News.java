package com.draizyyy.myreportcard.pojos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class News {
    @PrimaryKey(autoGenerate = true)
    public int id;
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
