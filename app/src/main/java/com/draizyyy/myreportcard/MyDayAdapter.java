package com.draizyyy.myreportcard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyDayAdapter extends RecyclerView.Adapter<MyDayAdapter.DayViewHolder> {
    private final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private final List<Day> list;

    MyDayAdapter(List<Day> list)
    {
        this.list = list;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup,
            int i)
    {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(
                        R.layout.timetable_day,
                        viewGroup, false);

        return new DayViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DayViewHolder dayViewHolder, int position)
    {
        final Day day = list.get(position);
        dayViewHolder.DayName.setText(day.getDay_name());
        dayViewHolder.DayDate.setText(day.getDate());

        LinearLayoutManager layoutManager = new LinearLayoutManager(dayViewHolder.LessonRecyclerView.getContext(), LinearLayoutManager.VERTICAL, false);

        layoutManager.setInitialPrefetchItemCount(day.getLessonsList().size());

        MyLessonAdapter myLessonAdapter = new MyLessonAdapter(day.getLessonsList());
        dayViewHolder.LessonRecyclerView.setLayoutManager(layoutManager);
        dayViewHolder.LessonRecyclerView.setAdapter(myLessonAdapter);
        dayViewHolder.LessonRecyclerView.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    static class DayViewHolder extends RecyclerView.ViewHolder {
        private final TextView DayName;
        private final RecyclerView LessonRecyclerView;
        private final TextView DayDate;

        DayViewHolder(View view)
        {
            super(view);
            DayName = view.findViewById(R.id.day_name);
            LessonRecyclerView = view.findViewById(R.id.day);
            DayDate = view.findViewById(R.id.day_date);
        }
    }
}

