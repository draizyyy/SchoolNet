package com.draizyyy.myreportcard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.draizyyy.myreportcard.databinding.ActivityGradesBinding;
import com.draizyyy.myreportcard.databinding.NewSBinding;
import com.draizyyy.myreportcard.databinding.ActivityNewsBinding;

public class GradeActivity extends Fragment {
    private final List<Grade> gradesList = new ArrayList<>();


    private ActivityGradesBinding gradesBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gradesBinding = gradesBinding.inflate(inflater, container, false);

        initData();
        setupUI();

        return gradesBinding.getRoot();
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

    private void initData() {
        gradesList.add(new Grade("Математика", 4.81));
        gradesList.add(new Grade("Русский язык", 4.23));
        gradesList.add(new Grade("Физика", 3.61));
        gradesList.add(new Grade("Математика", 4.81));
        gradesList.add(new Grade("Русский язык", 4.23));
        gradesList.add(new Grade("Физика", 3.61));
        gradesList.add(new Grade("Математика", 4.81));
        gradesList.add(new Grade("Русский язык", 4.23));
        gradesList.add(new Grade("Физика", 3.61));
        gradesList.add(new Grade("Математика", 4.81));
        gradesList.add(new Grade("Русский язык", 4.23));
        gradesList.add(new Grade("Физика", 3.61));
    }
}