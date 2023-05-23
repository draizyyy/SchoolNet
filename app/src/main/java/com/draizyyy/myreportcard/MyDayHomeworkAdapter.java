package com.draizyyy.myreportcard;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MyDayHomeworkAdapter extends RecyclerView.Adapter<MyDayHomeworkAdapter.MyDayHomeworkViewHolder> {
    private final List<Lesson> list;
    MyDayHomeworkAdapter(List<Lesson> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public MyDayHomeworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.homework,
                parent,
                false
        );
        return new MyDayHomeworkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDayHomeworkViewHolder holder, int position) {
        Lesson lesson = list.get(position);
        Log.v("MY APP", "name of lesson: " + lesson.name + ", homework: " + lesson.homework);
        holder.name.setText(lesson.getName());
        holder.homework.setText(lesson.homework);
        holder.checkBox.setChecked(lesson.isChecked);
        holder.checkBox.setOnClickListener(view -> {
            lesson.isChecked = !lesson.isChecked;
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyDayHomeworkViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView homework;
        private final CheckBox checkBox;
        public MyDayHomeworkViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.homework_name_of_lesson);
            homework = itemView.findViewById(R.id.text_of_homework);
            checkBox = itemView.findViewById(R.id.homework_check_box);
        }
    }
}
