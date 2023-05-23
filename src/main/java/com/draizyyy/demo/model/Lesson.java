package com.draizyyy.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Long dayId;
    public String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setFinish_time(String finish_time) {
        this.finish_time = finish_time;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public Long getId() {
        return id;
    }

    public Long getDayId() {
        return dayId;
    }

    public String getName() {
        return name;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getFinish_time() {
        return finish_time;
    }

    public String getGrade() {
        return grade;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getAddress() {
        return address;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getHomework() {
        return homework;
    }

    public String start_time;
    public String finish_time;
    public String grade;
    public String classroom;
    public String address;
    public String schoolName;
    public String teacher;
    public String homework;

    public Lesson(String name, String start_time, String finish_time) {
        this(name, start_time, finish_time, "", "", "", "", "", "");
    }
    public Lesson(String name, String start_time, String finish_time, String grade) {
        this(name, start_time, finish_time, grade, "", "", "", "", "");
    }
    public Lesson(String name, String start_time, String finish_time, String grade, String classroom) {
        this(name, start_time, finish_time, grade, classroom, "", "", "", "");
    }
    public Lesson(String name, String start_time, String finish_time, int grade, String classroom, String teacher, String homework) {
        this(name, start_time, finish_time, String.valueOf(grade), classroom, "Кленовый бульвар, 21", "Лицей 1523", teacher, homework);
    }
    public Lesson(String name, String start_time, String finish_time, String grade, String classroom, String address, String schoolName) {
        this(name, start_time, finish_time, grade, classroom, address, schoolName, "", "");
    }
    public Lesson(String name, String start_time, String finish_time, String grade, String classroom, String address, String schoolName, String teacher) {
        this(name, start_time, finish_time, grade, classroom, address, schoolName, teacher, "");
    }
    public Lesson(String name, String start_time, String finish_time, String grade, String classroom, String address, String schoolName, String teacher, String homework) {
        this.name = name;
        this.start_time = start_time;
        this.finish_time = finish_time;
        this.grade = grade;
        this.address = address;
        this.schoolName = schoolName;
        this.teacher = teacher;
        this.classroom = classroom;
        this.homework = homework;

        if (this.grade.equals("0")) {
            this.grade = "";
        }
    }
}
