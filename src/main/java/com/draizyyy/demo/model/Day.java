package com.draizyyy.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "days")
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public void setDay_name(String day_name) {
        this.day_name = day_name;
    }

    public void setLessonsList(List<Lesson> lessonsList) {
        LessonsList = lessonsList;
    }

    public void setDateOfDay(String dateOfDay) {
        this.dateOfDay = dateOfDay;
    }

    public Long getId() {
        return id;
    }

    public String getDay_name() {
        return day_name;
    }

    public List<Lesson> getLessonsList() {
        return LessonsList;
    }

    public String getDateOfDay() {
        return dateOfDay;
    }

    public String day_name;
    @OneToMany
    public List<Lesson> LessonsList;
    public String dateOfDay;

    public Day(String day_name) {
        this.day_name = day_name;
    }
    public Day(String day_name, String dateOfDay) {
        this.day_name = day_name;
    }

    public Day(String day_name, List<Lesson> LessonsList, String dateOfDay) {
        this.day_name = day_name;
        this.LessonsList = LessonsList;
        this.dateOfDay = dateOfDay;
    }
}

