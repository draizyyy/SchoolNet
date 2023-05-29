package com.draizyyy.myreportcard.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.draizyyy.myreportcard.App;
import com.draizyyy.myreportcard.adapters.MyGradeAdapter;
import com.draizyyy.myreportcard.databinding.ActivityGradesBinding;
import com.draizyyy.myreportcard.pojos.Grade;
import com.draizyyy.myreportcard.room.DayDao;

public class GradeActivity extends Fragment {
    private final List<Grade> gradesList = new ArrayList<>();
    private boolean isCreatingEnded = false;
    private Handler h;
    private ActivityGradesBinding gradesBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        h = new Handler();
        gradesBinding = ActivityGradesBinding.inflate(inflater, container, false);
        setupUI();
        isCreatingEnded = true;
        return gradesBinding.getRoot();
    }

    private void updateUI() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .detach(this)
                .attach(this)
                .commit();
    }

    private void setupUI() {
        gradesBinding.grades.setLayoutManager(new LinearLayoutManager(
                        getContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                )
        );
        gradesBinding.grades.setAdapter(new MyGradeAdapter(gradesList));
//        newsBinding.addButton.setOnClickListener(view -> {
//            list.add(Finances.getRandom());
//            binding.recyclerView.getAdapter().notifyItemChanged(list.size() - 1);
//        });
    }

    public void initData() {
//        gradesList.add(new Grade("Математика", 4.81));
        new Thread(() -> {
            DayDao dayDao = App.getDatabase().dayDao();
            List<String> names = dayDao.getAllLessonNames();
            for (String name: names) {
                List<String> grades = dayDao.getAllGradesForLesson(name);
                gradesList.add(new Grade(name, grades));
            }
            if (isCreatingEnded) {
                h.post(() -> setupUI());
                h.post(() -> updateUI());
                isCreatingEnded = false;
            }
        }).start();
    }
}