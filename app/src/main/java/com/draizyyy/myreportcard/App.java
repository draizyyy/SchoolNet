package com.draizyyy.myreportcard;

import android.app.Application;
import android.util.Log;

import androidx.room.Room;

import com.draizyyy.myreportcard.pojos.Day;
import com.draizyyy.myreportcard.pojos.News;
import com.draizyyy.myreportcard.retrofit.NetworkService;
import com.draizyyy.myreportcard.room.DayDao;
import com.draizyyy.myreportcard.room.DayDatabase;
import com.draizyyy.myreportcard.room.NewsDao;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    private static App instance;
    private static DayDatabase database;

    public static App getInstance() {
        return instance;
    }

    public static DayDatabase getDatabase() {
        return database;
    }

    private void setupDatabase() {
        NetworkService networkService = new NetworkService();
        new Thread(() -> {
            List<News> news = networkService.getNews();
            List<Day> days = networkService.getDays();

            NewsDao newsDao = App.getDatabase().newsDao();
            DayDao dayDao = App.getDatabase().dayDao();

//            newsList.add(new News("Администрация", "20 апреля", "Завтра уроки отменяются"));
//            newsList.add(new News("Королёв Б.И.", "17 апреля", "Завтра отменяются все уроки физики, потому что я сегодня добрый."));
//            newsList.add(new News("Кручинина О.Б.", "19 апреля", "Сегодня уроков не будет, можете идти домой"));
            dayDao.deleteAllLessons();
            dayDao.deleteAllDays();
            newsDao.deleteAllNews();

            newsDao.insertAllNews(news);
            dayDao.insertAllDays(days);
        }).start();
        Log.i("MY APP", "inserting done");
    }

    @Override
    public void onCreate() {
        instance = this;
        database = Room.databaseBuilder(this, DayDatabase.class, "database").build();
        setupDatabase();
        super.onCreate();
    }
}