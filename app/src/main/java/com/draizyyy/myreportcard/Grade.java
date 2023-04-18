package com.draizyyy.myreportcard;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Grade {
    @PrimaryKey
    public int id;
    public String lesson_name;
    public double grade;
    public Grade(String lesson_name, double grade) {
        this.lesson_name = lesson_name;
        this.grade = grade;
    }
}
