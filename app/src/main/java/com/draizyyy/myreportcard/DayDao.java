package com.draizyyy.myreportcard;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DayDao {
    @Query("SELECT * FROM Day WHERE id =:id")
    List<DayWithLessons> loadDayBy(int id);
    @Query("SELECT * FROM Day WHERE id =:id")
    DayWithLessons getDayBy(int id);
    @Transaction
    @Query("SELECT * FROM Day")
    List<DayWithLessons> loadDialogBy();
    // WHERE id = :id"
    @Transaction
    @Query("SELECT * FROM Day")
    DayWithLessons getDialogBy();
//    @Query("SELECT * FROM Day")
//    public List<DayWithLessons> loadDayWithLessons();
//    @Query("SELECT * FROM Lesson WHERE  =:dayId")
//    List<Lesson> getLessonList(int dayId);
    @Insert
    void insertAll(Day... days);

    @Delete
    void delete(Day day);

    @Insert
    void save(Day day);

    @Insert
    void insertLessonList(List<Lesson> lessons);

    @Update
    void update(Day day);

}
