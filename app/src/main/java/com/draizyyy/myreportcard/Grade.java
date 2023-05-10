package com.draizyyy.myreportcard;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.Objects;

@Entity
public class Grade {
    @PrimaryKey
    public int id;
    public String lesson_name;
    public double grade;
    public Grade(String lesson_name, List<String> grades) {
        this.lesson_name = lesson_name;
        this.grade = countGrade(grades);
    }
    private double countGrade(List<String> grades) {
        int amountOfGrades = 0;
        int sumOfGrades = 0;
        for (String grade: grades) {
            ++amountOfGrades;
            if (!Objects.equals(grade, "")) {
                sumOfGrades += Integer.parseInt(grade);
            }
        }
        return (double) sumOfGrades/amountOfGrades;
    }
}
