package com.draizyyy.myreportcard;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DayDao {
    @Insert
    void insertAll(Day... week);

    @Delete
    void delete(Day day);

    @Query("SELECT * FROM day")
    List<Day> getAllWeeks();
}
