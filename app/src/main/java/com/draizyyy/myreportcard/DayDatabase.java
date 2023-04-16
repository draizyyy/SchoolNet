package com.draizyyy.myreportcard;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Day.class, Lesson.class}, version = 1)
public abstract class DayDatabase extends RoomDatabase {
    public static final String NAME = "Days";
    public abstract DayDao dayDao();
}
