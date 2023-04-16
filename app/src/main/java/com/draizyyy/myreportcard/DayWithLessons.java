package com.draizyyy.myreportcard;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class DayWithLessons {
    @Embedded
    public Day day;

    @Relation(parentColumn = "id", entityColumn = "dayId", entity = Lesson.class)
    public List<Lesson> lessons;
}
