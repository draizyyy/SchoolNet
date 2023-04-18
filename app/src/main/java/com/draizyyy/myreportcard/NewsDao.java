package com.draizyyy.myreportcard;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.List;

public interface NewsDao {
    @Insert
    void insertAll(News... news);

    @Delete
    void delete(News news);

    @Insert
    void save(News news);

    @Insert
    void insertLessonList(List<News> news);

    @Update
    void update(News news);
}
