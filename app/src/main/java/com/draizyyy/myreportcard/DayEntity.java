package com.draizyyy.myreportcard;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import java.util.List;

@Entity
public class DayEntity {

    @PrimaryKey
    @ColumnInfo(name = "dayid")
    private String dayId;

    @ColumnInfo(name = "dayname")
    private String dayName;

    @ColumnInfo(name = "daydate")
    private String dayDate;

    public DayEntity(String dayId, String dayName, String dayDate) {
        this.dayId = dayId;
        this.dayName = dayName;
        this.dayDate = dayDate;
    }
}
