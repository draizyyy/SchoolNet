package com.draizyyy.myreportcard;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Lesson {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int dayId;
    @ColumnInfo(name = "Lesson name")
    @NotNull
    public String name;
    @ColumnInfo(name = "Start time")
    public String start_time;
    @ColumnInfo(name = "End time")
    public String finish_time;
    @ColumnInfo(name = "Grade")
    public String grade;
    @ColumnInfo(name = "Classroom")
    public String classroom;
    @ColumnInfo(name = "Address")
    public String address;
    @ColumnInfo(name = "School name")
    public String schoolName;
    @ColumnInfo(name = "Teacher")
    public String teacher;
    @ColumnInfo(name = "Homework")
    public String homework;
    public Lesson(@NonNull String name, String start_time, String finish_time) {
        this(name, start_time, finish_time, "", "", "", "", "", "");
    }
    @Ignore
    public Lesson(@NonNull String name, String start_time, String finish_time, String grade) {
        this(name, start_time, finish_time, grade, "", "", "", "", "");
    }
    @Ignore
    public Lesson(@NonNull String name, String start_time, String finish_time, String grade, String classroom) {
        this(name, start_time, finish_time, grade, classroom, "", "", "", "");
    }
    @Ignore
    public Lesson(@NonNull String name, String start_time, String finish_time, String grade, String classroom, String address) {
        this(name, start_time, finish_time, grade, classroom, address, "", "", "");
    }
    @Ignore
    public Lesson(@NonNull String name, String start_time, String finish_time, String grade, String classroom, String address, String schoolName) {
        this(name, start_time, finish_time, grade, classroom, address, schoolName, "", "");
    }
    @Ignore
    public Lesson(@NonNull String name, String start_time, String finish_time, String grade, String classroom, String address, String schoolName, String teacher) {
        this(name, start_time, finish_time, grade, classroom, address, schoolName, teacher, "");
    }
    @Ignore
    public Lesson(@NonNull String name, String start_time, String finish_time, String grade, String classroom, String address, String schoolName, String teacher, String homework) {
        this.name = name;
        this.start_time = start_time;
        this.finish_time = finish_time;
        this.grade = grade;
        this.address = address;
        this.schoolName = schoolName;
        this.teacher = teacher;
        this.classroom = classroom;
        this.homework = homework;
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

