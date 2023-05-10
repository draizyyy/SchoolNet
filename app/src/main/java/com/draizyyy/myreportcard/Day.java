package com.draizyyy.myreportcard;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Day {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "Day name")
    @NotNull
    public String day_name;
    @Ignore
    public List<Lesson> LessonsList;
    @ColumnInfo(name = "Date of day")
    public String dateOfDay;
    public Day(String day_name) {
        this.day_name = day_name;
    }
    public Day(@NonNull String day_name, List<Lesson> LessonsList) {
        this.day_name = day_name;
        this.LessonsList = LessonsList;
        this.dateOfDay = getDate(day_name);
    }
    @Ignore
    public Day(@NonNull String day_name, List<Lesson> LessonsList, String dateOfDay) {
        this.day_name = day_name;
        this.LessonsList = LessonsList;
        this.dateOfDay = dateOfDay;
    }

    public String getDate(String day_name) {
        int numberOfDay = getNumberOfDay(day_name);
        int numberOfToday = Date.getTodayDateNumber();

        if (numberOfToday == numberOfDay) {
            Log.v("MY APP", Date.getDate());
            return Date.getDate();
        }
        else {
            int difference = numberOfDay - numberOfToday;
            Log.v("MY APP", Date.getDate(difference));
            return Date.getDate(difference);
        }
    }

    private int getNumberOfDay(String day_name) {
        int numberOfDay;
        switch (day_name) {
            case "Понедельник":
                numberOfDay = 0;
                break;
            case "Вторник":
                numberOfDay = 1;
                break;
            case "Среда":
                numberOfDay = 2;
                break;
            case "Четверг":
                numberOfDay = 3;
                break;
            case "Пятница":
                numberOfDay = 4;
                break;
            case "Суббота":
                numberOfDay = 5;
                break;
            case "Воскресенье":
                numberOfDay = 6;
                break;
            default:
                numberOfDay = -1;
        }
        return numberOfDay;
    }

    public List<Lesson> getLessonsList() {
        return LessonsList;
    }

    public String getDay_name() {
        return day_name;
    }

    public String getDate() {
        return dateOfDay;
    }

    public void setDate(String date) {
        this.dateOfDay = date;
    }

    public void setDay_name(@NonNull String day_name) {
        this.day_name = day_name;
    }

    public void setLessonsList(List<Lesson> lessonsList) {
        LessonsList = lessonsList;
    }
}
