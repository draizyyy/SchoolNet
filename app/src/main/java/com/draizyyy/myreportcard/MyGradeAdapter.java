package com.draizyyy.myreportcard;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.draizyyy.myreportcard.Lesson;
import com.draizyyy.myreportcard.MyLessonAdapter;
import com.draizyyy.myreportcard.News;
import com.draizyyy.myreportcard.R;

import java.util.List;

public class MyGradeAdapter extends RecyclerView.Adapter<MyGradeAdapter.MyViewHolder> {
    private final List<Grade> list;

    public MyGradeAdapter(List<Grade> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.grade,
                parent,
                false
        );
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyGradeAdapter.MyViewHolder holder, int position) {
        final Grade grade = list.get(position);
        Log.i("MY APP", grade.lesson_name);
        holder.name.setText(grade.lesson_name);
        if (grade.grade != null) {
            holder.grade.setText(String.valueOf(grade.grade));
        }
        else {
            holder.grade.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView grade;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_of_lesson);
            grade = itemView.findViewById(R.id.grade);
        }
    }
}