package com.draizyyy.myreportcard.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.draizyyy.myreportcard.App;
import com.draizyyy.myreportcard.adapters.MyHomeworkAdapter;
import com.draizyyy.myreportcard.databinding.ActivityHomeworkBinding;
import com.draizyyy.myreportcard.pojos.Day;
import com.draizyyy.myreportcard.room.DayDao;

import java.util.ArrayList;
import java.util.List;

public class HomeworksActivity extends Fragment {
    private final List<Day> dayList = new ArrayList<>();
    private Handler h;
    private boolean isCreatingEnded = false;
    private ActivityHomeworkBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeworkBinding.inflate(inflater, container, false);
        h = new Handler();
        setupUI();
        isCreatingEnded = true;

        Log.i("MY APP", "creating done");
        return binding.getRoot();
    }
    private void updateUI() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .detach(this)
                .attach(this)
                .commit();
    }
    private void setupUI() {

        // Initialise the Linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        // Pass the arguments
        // to the parentItemAdapter.
        // These arguments are passed
        // using a method ParentItemList()
        MyHomeworkAdapter myHomeworkAdapter = new MyHomeworkAdapter(dayList);

        // Set the layout manager
        // and adapter for items
        // of the parent recyclerview
        binding.homeworkDays.setAdapter(myHomeworkAdapter);
        binding.homeworkDays.setLayoutManager(layoutManager);


//        weekBinding.timetable.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        weekBinding.timetable.setAdapter(new MyDayAdapter(dayList));
    }
    public void initData() {
        new Thread(() -> {
            DayDao dayDao = App.getDatabase().dayDao();
            Log.i("MY APP", "got dao");
            dayList.addAll(dayDao.getAllDayWithNotNullLessonsHomework());
//            Day day = dayList.get(2);
//            Log.i("MY APP", "timetable dayList 2 day lessonList size: " + day.LessonsList.size());
            if (isCreatingEnded) {
                h.post(() -> setupUI());
                h.post(() -> updateUI());
                isCreatingEnded = false;
            }
        }).start();
        Log.i("MY APP", "done1");
    }
}
