package com.draizyyy.myreportcard;

public class Lesson {
    private String name;
    private String start_time;
    private String finish_time;
    private String grade;

    public Lesson(String name, String start_time, String finish_time) {
        this(name, start_time, finish_time, "");
    }

    public Lesson(String name, String start_time, String finish_time, String grade) {
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

    public void setFinish_time(String finish_time) {
        this.finish_time = finish_time;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }


}
