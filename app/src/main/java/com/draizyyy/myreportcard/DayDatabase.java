package com.draizyyy.myreportcard;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Day.class, Lesson.class}, version = 1)
public abstract class DayDatabase extends RoomDatabase {
    public abstract DayDao dayDao();
}
