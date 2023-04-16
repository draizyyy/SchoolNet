package com.draizyyy.myreportcard;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Lesson {
    @PrimaryKey
    public int id;
    public int dayId;
    @ColumnInfo(name = "Lesson name")
    @NotNull
    public String name;
    @ColumnInfo(name = "Start time")
    @NotNull
    public String start_time;
    @ColumnInfo(name = "End time")
    @NotNull
    public String finish_time;
    @ColumnInfo(name = "grade")
    public String grade;

    public Lesson(String name, String start_time, String finish_time) {
        this(name, start_time, finish_time, "");
    }

    public Lesson(@NonNull String name, @NonNull String start_time, @NonNull String finish_time, String grade) {
        this.name = name;
        this.start_time = start_time;
        this.finish_time = finish_time;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getFinish_time() {
        return finish_time;
    }

    public String getGrade() {
        return grade;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setFinish_time(@NonNull String finish_time) {
        this.finish_time = finish_time;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setStart_time(@NonNull String start_time) {
        this.start_time = start_time;
    }


}
