package com.draizyyy.myreportcard.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.draizyyy.myreportcard.pojos.News;

import java.util.List;

@Dao
public interface NewsDao {
    @Insert
    void insertAllNews(List<News> news);
    @Delete
    void delete(News news);
    @Query("SELECT * FROM News")
    public abstract List<News> getAllNews();
    @Insert
    void save(News news);
    @Query("DELETE FROM News")
    public abstract void deleteAllNews();
    @Insert
    void insertLessonList(List<News> news);
    @Update
    void update(News news);
}
