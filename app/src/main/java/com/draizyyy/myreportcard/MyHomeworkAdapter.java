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

public class MyHomeworkAdapter extends RecyclerView.Adapter<MyHomeworkAdapter.HomeworkViewHolder> {
    private final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private final List<Day> list;

    MyHomeworkAdapter(List<Day> list)
    {
        this.list = list;
    }

    @NonNull
    @Override
    public HomeworkViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup,
            int i)
    {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(
                        R.layout.homework_day,
                        viewGroup, false);

        return new HomeworkViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull HomeworkViewHolder homeworkViewHolder, int position)
    {
        final Day day = list.get(position);
        homeworkViewHolder.dayName.setText(day.getDate());

        LinearLayoutManager layoutManager = new LinearLayoutManager(homeworkViewHolder.homeworkRecyclerView.getContext(), LinearLayoutManager.VERTICAL, false);

        layoutManager.setInitialPrefetchItemCount(day.getLessonsList().size());

        MyDayHomeworkAdapter myDayHomeworkAdapter = new MyDayHomeworkAdapter(day.getLessonsList());
        RecyclerView recyclerView = homeworkViewHolder.homeworkRecyclerView;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myDayHomeworkAdapter);
        recyclerView.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    static class HomeworkViewHolder extends RecyclerView.ViewHolder {
        private final TextView dayName;
        private final RecyclerView homeworkRecyclerView;
        HomeworkViewHolder(View view)
        {
            super(view);
            dayName = view.findViewById(R.id.homework_date);
            homeworkRecyclerView = view.findViewById(R.id.homework_homeworks);
        }
    }
}
