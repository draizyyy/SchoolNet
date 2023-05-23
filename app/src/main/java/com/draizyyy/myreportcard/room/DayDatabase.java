package com.draizyyy.myreportcard.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.draizyyy.myreportcard.pojos.Day;
import com.draizyyy.myreportcard.pojos.Lesson;
import com.draizyyy.myreportcard.pojos.News;
import com.draizyyy.myreportcard.pojos.User;

@Database(entities = {Day.class, Lesson.class, User.class, News.class}, version = 1)
public abstract class DayDatabase extends RoomDatabase {
    public abstract DayDao dayDao();
    public abstract NewsDao newsDao();
}
