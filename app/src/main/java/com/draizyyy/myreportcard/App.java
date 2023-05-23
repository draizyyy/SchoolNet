package com.draizyyy.myreportcard;

import android.app.Application;
import android.util.Log;

import androidx.room.Room;

import com.draizyyy.myreportcard.DayDatabase;
import com.draizyyy.myreportcard.DayDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
//        final List<Lesson> lessonList = new ArrayList<>();
        NetworkService networkService = new NetworkService();
        new Thread(() -> {
            List<Day> dayList = networkService.getDays();
            Log.i("MY APP", String.valueOf(dayList.size()));
            DayDao dayDao = App.getDatabase().dayDao();
            Log.i("MY APP", "dayList.size:" + dayList.size());
            dayDao.deleteAllLessons();
            dayDao.deleteAllDays();
            dayDao.insertAllDays(dayList);
        }).start();
        Log.i("MY APP", "inserting done");
//        Lesson lesson = new Lesson("Литература", "08:45", "09:30", "5", "215", "Пролетарский проспект", "Лицей 1511", "Андреев М.Ф.", "Прочитать \"Бэлу\", подготовить ответы с опорой на текст на вопросы из ДЗ: закладки/выписанные на листок с заданием номера страниц. Учим и сдаём второе из пяти стихотворений Лермонтова. И ещё много много много много много много много много много много много много много много много много много много много много текста");
//        Lesson lesson1 = new Lesson("Английский язык", "09:50", "10:35");
//        Lesson lesson2 = new Lesson("Информатика", "10:50", "11:35","4", "123", "", "", "Янков В.Ю.");
//        Lesson lesson3 = new Lesson("Алгебра", "11:45", "12:30");
//        Lesson lesson4 = new Lesson("Алгебра", "12:45", "13:30", "5", "21А", "Кленовый Бульвар", "Лицей 1523", "Алексеева Н.И.", "А-9, &22, № 22.6, 22.8, 22.14, 22.16, 22.20");
//        Lesson lesson5 = new Lesson("Химия", "13:45", "14:30", "4", "", "", "", "", "Знать определения.");
//        Lesson lesson6 = new Lesson("Обществознание", "14:35", "15:35", "3","37", "Аоаоаоа", "Лицей 1123", "Никитин А.П.", "Прочитать §16, ответить на вопросы на стр. 64-65.");
//        Lesson lesson7 = new Lesson("Информатика", "15:45", "16:30", "5", "52", "", "Лицей 1523");
//        Lesson lesson8 = new Lesson("Основы безопаснос...", "16:45", "17:30");
//
//        lessonList.add(lesson);
//        lessonList.add(lesson1);
//        lessonList.add(lesson2);
//        lessonList.add(lesson3);
//        lessonList.add(lesson4);
//        lessonList.add(lesson5);
//        lessonList.add(lesson6);
//        lessonList.add(lesson7);
//        lessonList.add(lesson8);
//
//        Day day0 = new Day("Воскресенье", new ArrayList<>(), "12 марта");
//        Day day1 = new Day("Понедельник", lessonList, "13 марта");
//        Day day2 = new Day("Вторник", lessonList, "14 марта");
//        Day day3 = new Day("Среда", lessonList, "15 марта");
//        Day day4 = new Day("Четверг", lessonList, "16 марта");
//        Day day5 = new Day("Пятница", lessonList, "17 марта");
//        Day day6 = new Day("Суббота", lessonList, "18 марта");
//        Day day7 = new Day("Воскресенье", lessonList, "19 марта");
//        Day day8 = new Day("Понедельник", lessonList, "20 марта");
//
//        dayList.add(day0);
//        dayList.add(day1);
//        dayList.add(day2);
//        dayList.add(day3);
//        dayList.add(day4);
//        dayList.add(day5);
//        dayList.add(day6);
//        dayList.add(day7);
//        dayList.add(day8);


    }

    @Override
    public void onCreate() {
        instance = this;
        database = Room.databaseBuilder(this, DayDatabase.class, "database").build();
        setupDatabase();
        super.onCreate();
    }
}